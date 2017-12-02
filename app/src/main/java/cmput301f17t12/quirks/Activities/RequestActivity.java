package cmput301f17t12.quirks.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import cmput301f17t12.quirks.Adapters.RequestListItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Drop;

import cmput301f17t12.quirks.Models.Request;
import cmput301f17t12.quirks.Models.TradeRequest;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class RequestActivity extends  SocialActivity{

    public User currentlylogged;
    private RequestListItemAdapter adapter;
    private ArrayList<User> requestlist;
    private SparseArray<ArrayList<Drop>> giveMap;
    private SparseArray<ArrayList<Drop>> receiveMap;

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
        ArrayList<Request> requests = new ArrayList<>();

        // Must add trade requests first or getGiveInfo & getReceiveInfo breaks.
        // Indexing of the adapter relies on the order of addition
        requests.addAll(currentlylogged.getTradeRequests());
        requests.addAll(currentlylogged.getUserRequests());

        giveMap = new SparseArray<>();
        receiveMap = new SparseArray<>();
        buildMaps(currentlylogged.getTradeRequests());

        ListView lView = (ListView)findViewById(R.id.listviewRequest);

        adapter = new RequestListItemAdapter(requests,this);
        lView.setAdapter(adapter);
    }

    void buildMaps(ArrayList<TradeRequest> tradeRequests) {
        for (TradeRequest item : tradeRequests) {
            giveMap.put(tradeRequests.indexOf(item), item.getMyDrop());
            receiveMap.put(tradeRequests.indexOf(item), item.getFromUserDrop());
        }
    }

    public ArrayList<Drop> getGiveInfo(int index) {
        return giveMap.get(index);
    }

    public ArrayList<Drop> getReceiveInfo(int index) {
        return receiveMap.get(index);
    }

    public void acceptRequest(int AcceptFriendPos){


    }
    public void declineRequest(int DeleteFriendPos){

    }

    public void acceptFriend(int AcceptFriendPos){
        User friend = requestlist.get(AcceptFriendPos);
        currentlylogged.addFriend(friend);

        ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
        adapter.notifyDataSetChanged();

    }
    public void declineFriend(int DeleteFriendPos){
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
