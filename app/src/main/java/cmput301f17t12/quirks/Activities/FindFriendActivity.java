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
        Button addFriendButton = (Button) findViewById(R.id.buttonAddFriend);
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

    public void findFriendSearchBut(View view){
        String stringSearchUser = searchFriendUser.getText().toString();
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
            UserfromQueries =getUsersTask.get();
            Log.d(TAG, "findFriendSearchBut: " + UserfromQueries.get(0).getUsername());
            Log.d(TAG, "findFriendSearchBut: " + UserfromQueries.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        userList.clear();
        userList.addAll(UserfromQueries);
        Log.d(TAG, "findFriendSearchBut: the userlist now with the userlist i found  is " + userList.size());
        Log.d(TAG, "findFriendSearchBut: the userlist now with the userlist i found  is " + userList.get(0).getUsername());
        adapter.notifyDataSetChanged();
    }
    
    

    public void addFriendsBut(View view){
        LinearLayout vwParentRow = (LinearLayout) view.getParent();
        Log.d(TAG, "addFriendsBut: im pressing the button");


    }

    public String getQueryFilterUser(){
        String query = "user";
        // for the user
        // look in quirklist
        // match where type is type
        return query;
    }

    public void addFriend(int selectFriendIndex) {
        Toast.makeText(FindFriendActivity.this,"Sending Friend Request",Toast.LENGTH_SHORT).show();
        String username = userList.get(selectFriendIndex).getUsername();
        Log.d(TAG, "addFriend: the index is this " + username);
        currentlylogged.sendFriendRequest(userList.get(selectFriendIndex));
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
