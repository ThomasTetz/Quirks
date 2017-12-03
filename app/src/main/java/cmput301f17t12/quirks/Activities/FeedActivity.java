package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Adapters.FollowFeedItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class FeedActivity extends SocialActivity {
    private String jestID;
    private FollowFeedItemAdapter adapter;
    private ArrayList<Quirk> quirkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        jestID = settings.getString("jestID", "defaultvalue");
        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        final User currentlylogged = HelperFunctions.getUserObject(jestID);
        ArrayList<String> friendslist = currentlylogged.getFriends();
        StringBuilder query = new StringBuilder("{" +
                "  \"query\": {" +
                "    \"filtered\": {" +
                "      \"filter\":  {" +
                "        \"terms\": {" +
                "          \"username\": [");
        for (int i = 0; i < friendslist.size(); i++) {
            query.append("\"").append(friendslist.get(i)).append("\"");
            if (i != friendslist.size() - 1) {
                query.append(", ");
            }
        }
        query.append("           ]" + "        }" + "      }" + "    }" + "  }" + "}");
        ArrayList<User> followingUsers = HelperFunctions.getAllUsers(query.toString());

        ArrayList<String> userlist = getQuirks(followingUsers);

        // create instance of custom adapter
        adapter = new FollowFeedItemAdapter(userlist, quirkList, this);

        // create listView and assign custom adapter
        ListView lView = (ListView) findViewById(R.id.listViewFeed);
        lView.setAdapter(adapter);


    }

    public ArrayList<String> getQuirks(ArrayList<User> followingUsers) {
        ArrayList<String> out = new ArrayList<>();
        for (User user : followingUsers) {
            for (Quirk quirk : user.getQuirks().getList()) {
                quirkList.add(quirk);
                out.add(user.getUsername());
            }
        }
        return out;
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_feed;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_feed;
    }
}
