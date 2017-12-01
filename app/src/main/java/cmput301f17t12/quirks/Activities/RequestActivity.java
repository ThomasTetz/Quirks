package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Adapters.FriendListItemAdapter;
import cmput301f17t12.quirks.Adapters.RequestListItemAdapter;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
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
        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        Inventory dummyInv = new Inventory();
        ArrayList<User> friends = new ArrayList<>();
        ArrayList<User> requests = new ArrayList<>();
        QuirkList quirks = new QuirkList();

        User dummy = new User("dummy",dummyInv,friends,requests,quirks);
        User dummy2 = new User("dummy2",dummyInv,friends,requests,quirks);
        User dummy3 = new User("Alex",dummyInv,friends,requests,quirks);

        //dummy.sendFriendRequest(currentlylogged,dummy);
        //dummy2.sendFriendRequest(currentlylogged,dummy2);
        //dummy3.sendFriendRequest(currentlylogged,dummy3);

        requestlist = currentlylogged.getRequests();
        requestlist.add(dummy);
        requestlist.add(dummy2);
        requestlist.add(dummy3);
        //Log.d(TAG, "onCreate: the size of requestlist is " + requestlist.size());


        adapter = new RequestListItemAdapter(requestlist,this);
        ListView lView = (ListView)findViewById(R.id.listviewRequest);
        lView.setAdapter(adapter);


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
