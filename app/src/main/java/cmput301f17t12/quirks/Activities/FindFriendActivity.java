package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cmput301f17t12.quirks.Adapters.FindFriendListItemAdapter;
import cmput301f17t12.quirks.Adapters.FriendListItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.Request;
import cmput301f17t12.quirks.Models.TradeRequest;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.Models.UserRequest;
import cmput301f17t12.quirks.R;

/**
 * Created by root on 11/30/17.
 */

public class FindFriendActivity extends SocialActivity {
    public User currentlylogged;
    private FindFriendListItemAdapter adapter;
    private ArrayList<User> userList;
    private static final String TAG = "FindFriendActivity" ;

    EditText searchFriendUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userList = new ArrayList<>();
        searchFriendUser = (EditText)findViewById(R.id.editTextFindFriend);
        ImageButton searchFriendButton = (ImageButton) findViewById(R.id.imageButtonSearch);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        Inventory dummyInv = new Inventory();
        ArrayList<User> friends = new ArrayList<>();
        QuirkList quirks = new QuirkList();
        ArrayList<TradeRequest> traderequests = new ArrayList<>();
        ArrayList<UserRequest> requests = new ArrayList<>();

        User dummy = new User("dummy",dummyInv,friends,requests, traderequests, quirks);
        User dummy2 = new User("dummy2",dummyInv,friends,requests, traderequests, quirks);
        User dummy3 = new User("Alex",dummyInv,friends,requests, traderequests, quirks);

        currentlylogged = HelperFunctions.getUserObject(jestID);

       /* userList.add(dummy);
        userList.add(dummy2);
        userList.add(dummy3);
        */

        ListView lView = (ListView)findViewById(R.id.findfriend_listview);
        adapter = new FindFriendListItemAdapter(userList,this);
        lView.setAdapter(adapter);

    }

    public void findFriendSearchBut(View view) {
        String stringSearchUser = searchFriendUser.getText().toString();
        if(stringSearchUser.equals(currentlylogged.getUsername())){
            Toast.makeText(FindFriendActivity.this,"Cannot input yourself",Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringSearchUser.equals("")) {
            Toast.makeText(FindFriendActivity.this,"Please input a username",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(FindFriendActivity.this,"Searching for user",Toast.LENGTH_SHORT).show();
            String query = "{" +
                    "  \"query\": {" +
                    "    \"match\": {" +
                    "      \"username\": \"" + stringSearchUser + "\"" +
                    "    }" +
                    "  }" +
                    "}";

            ElasticSearchUserController.GetUsersTask getUsersTask = new ElasticSearchUserController.GetUsersTask();
            getUsersTask.execute(query);
            ArrayList<User> UserfromQueries = new ArrayList<>();
            UserfromQueries.clear();
            Log.d(TAG, "findFriendSearchBut: the userfromqueries is " + UserfromQueries.size());
            try {
                UserfromQueries = getUsersTask.get();
            } catch (InterruptedException e) {
                Toast.makeText(FindFriendActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (ExecutionException e) {
                Toast.makeText(FindFriendActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            userList.clear();
            userList.addAll(UserfromQueries);
            if(userList.size() == 0){
                Toast.makeText(FindFriendActivity.this,"User does not exist",Toast.LENGTH_SHORT).show();
            }
            else {
                adapter.notifyDataSetChanged();
                }
            }
    }


    public void addFriend(int selectFriendIndex) {
        Toast.makeText(FindFriendActivity.this,"Sending Friend Request",Toast.LENGTH_SHORT).show();
        String username = userList.get(selectFriendIndex).getUsername();
        Log.d(TAG, "addFriend: the index is this " + username);
       // currentlylogged.sendFriendRequest(userList.get(selectFriendIndex));
    }

        @Override
    int getContentViewId() {
        return R.layout.activity_findfriends;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_findFriends;
    }
}
