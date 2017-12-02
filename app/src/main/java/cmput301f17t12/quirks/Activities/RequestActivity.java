package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Adapters.FriendListItemAdapter;
import cmput301f17t12.quirks.Adapters.RequestListItemAdapter;
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

public class RequestActivity extends  SocialActivity{

    public User currentlylogged;
    private RequestListItemAdapter adapter;
    private ArrayList<User> requestlist;
    private static final String TAG = "RequestActivity" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestlist = new ArrayList<>();

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        Inventory dummyInv = new Inventory();
        ArrayList<User> friends = new ArrayList<>();
        QuirkList quirks = new QuirkList();
        ArrayList<TradeRequest> traderequests = new ArrayList<>();
        ArrayList<UserRequest> requests = new ArrayList<>();

        User dummy = new User("dummy",dummyInv,friends,requests, traderequests, quirks);
        User dummy2 = new User("dummy2",dummyInv,friends,requests, traderequests, quirks);
        User dummy3 = new User("Alex",dummyInv,friends,requests, traderequests, quirks);

//        dummy.sendFriendRequest(currentlylogged);
//        dummy2.sendFriendRequest(currentlylogged);
//        dummy3.sendFriendRequest(currentlylogged);

//        requestlist = currentlylogged.getRequests();
       /* requestlist.add(dummy);
        requestlist.add(dummy2);
        requestlist.add(dummy3);
        */
        Log.d(TAG, "onCreate: the requestlist is " + requestlist.size());
        ListView lView = (ListView)findViewById(R.id.listviewRequest);

        adapter = new RequestListItemAdapter(requestlist,this);
        lView.setAdapter(adapter);


    }


    public void AcceptFriend(int AcceptFriendPos){
        User friend = requestlist.get(AcceptFriendPos);
        Log.d(TAG, "AcceptFriend: the currentlylog before is " + currentlylogged.getFriends().size());
        currentlylogged.addFriend(friend);
       // currentlylogged.deleteRequest(friend);
        Log.d(TAG, "AcceptFriend: the currentlylog now has " + currentlylogged.getFriends().size());
        ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
        adapter.notifyDataSetChanged();

    }
    public void DeleteFriend(int DeleteFriendPos){
        User friend = requestlist.get(DeleteFriendPos);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_requests;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_request;
    }
}
