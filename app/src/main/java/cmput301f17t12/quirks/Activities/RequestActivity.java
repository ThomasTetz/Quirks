package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Adapters.RequestListItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Drop;

import cmput301f17t12.quirks.Models.Request;
import cmput301f17t12.quirks.Models.TradeRequest;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.Models.UserRequest;
import cmput301f17t12.quirks.R;

public class RequestActivity extends  SocialActivity{

    public User currentlylogged;
    private RequestListItemAdapter adapter;
    private ArrayList<TradeRequest> tradeRequests;
    private ArrayList<UserRequest> userRequests;
    private ArrayList<Request> requests;

    private SparseArray<ArrayList<Drop>> giveMap;
    private SparseArray<ArrayList<Drop>> receiveMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requests = new ArrayList<>();

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);
        requests = new ArrayList<>();

        userRequests = currentlylogged.getUserRequests();
        tradeRequests = currentlylogged.getTradeRequests();

        updateRequests();

        giveMap = new SparseArray<>();
        receiveMap = new SparseArray<>();
        buildMaps(currentlylogged.getTradeRequests());

        ListView lView = (ListView)findViewById(R.id.listviewRequest);

        adapter = new RequestListItemAdapter(requests,this);
        lView.setAdapter(adapter);
    }

    void updateRequests() {
        requests.clear();
        // Must add trade requests first or getGiveInfo & getReceiveInfo breaks.
        // Indexing of the adapter relies on the order of addition

        requests.addAll(tradeRequests);
        requests.addAll(userRequests);
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

    public void acceptRequest(int pos){
        Request request = requests.get(pos);
        // Trade request
        if (request.getDetails() == null) {
            acceptTrade(pos);
        } else {
            int friendindex = pos - tradeRequests.size();
            acceptFriend(friendindex);
        }
    }

    public void declineRequest(int pos){
        Request request = requests.get(pos);
        // Trade request
        if (request.getDetails() == null) {
            declineTrade(pos);
        } else {
            int friendindex = pos - tradeRequests.size();
            declineFriend(friendindex);
        }
    }

    public void acceptTrade(int pos) {

    }

    public void declineTrade(int pos) {

    }

    public void acceptFriend(int pos){
        UserRequest friend = userRequests.get(pos);
        User toAdd = HelperFunctions.getSingleUser(friend.getFromUser());
        currentlylogged.addFriend(toAdd.getUsername());
        toAdd.addFriend(currentlylogged.getUsername());

        userRequests.remove(friend);
        updateRequests();
        adapter.notifyDataSetChanged();

        ElasticSearchUserController.UpdateUserTask updateUserTask1 = new ElasticSearchUserController.UpdateUserTask();
        ElasticSearchUserController.UpdateUserTask updateUserTask2 = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask1.execute(currentlylogged);
        updateUserTask2.execute(toAdd);

    }
    public void declineFriend(int pos){
        UserRequest friend = userRequests.get(pos);
        userRequests.remove(friend);
        updateRequests();
        adapter.notifyDataSetChanged();
        ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
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
