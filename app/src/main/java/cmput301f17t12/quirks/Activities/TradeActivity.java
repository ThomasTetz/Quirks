package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Adapters.CollectibleItemAdapter;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Drop;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class TradeActivity extends SocialActivity {

    private User currentlylogged;
    private User tradedUser;
    private CollectibleItemAdapter loggeduserAdapter;
    private CollectibleItemAdapter tradeduserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Spinner dropdown = (Spinner) findViewById(R.id.collectionspinner);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        if (currentlylogged != null) {
//            String query = "{" +
//                    "  \"from\": 0, \"size\": 5000, " +
//                    "  \"query\": {" +
//                    "    \"match_all\": {}" +
//                    "  }" +
//                    "}";

//            String query = "{" +
//                    "  \"from\": 0, \"size\": 5000, " +
//                    "  \"query\": {" +
//                    "    \"bool\": {" +
//                    "      \"must_not\": {" +
//                    "        \"username\": \"" + currentlylogged.getUsername() + "\"" +
//                    "      }" +
//                    "    }" +
//                    "  }" +
//                    "}";
            String query = "{" +
                    "  \"from\": 0, \"size\": 5000, " +
                    "  \"query\": {" +
                    "    \"bool\": {" +
                    "      \"must_not\": {" +
                    "        \"term\": { \"username\" : \"" + currentlylogged.getUsername() + "\"}"+
                    "      }" +
                    "    }" +
                    "  }" +
                    "}";

            ArrayList<User> allusers = HelperFunctions.getAllUsers(query);

            if (allusers != null) {
                ArrayAdapter<User> useradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allusers);
                dropdown.setAdapter(useradapter);

                final TextView myUsername = (TextView) findViewById(R.id.usernametext);
                final TextView theirUsername = (TextView) findViewById(R.id.theirusername);
                Button tradebutton = (Button) findViewById(R.id.tradebtn);

                tradedUser = (User)dropdown.getSelectedItem();

                myUsername.setText(currentlylogged.getUsername());
                theirUsername.setText(tradedUser.getUsername());

                // instantiate custom adapter for both inventories
                loggeduserAdapter = new CollectibleItemAdapter(currentlylogged.getInventory().getList(), this);

                final ArrayList<Drop> tmpInventory = tradedUser.getInventory().getList();
                tradeduserAdapter = new CollectibleItemAdapter(tmpInventory, this);

                // handle listview and assign adapter
                ListView yourCollection = (ListView) findViewById(R.id.yourcollection);
                ListView theircollection = (ListView) findViewById(R.id.theircollection);
                yourCollection.setAdapter(loggeduserAdapter);
                theircollection.setAdapter(tradeduserAdapter);

                dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        tradedUser = (User)parentView.getItemAtPosition(position);
                        tmpInventory.clear();
                        tmpInventory.addAll(tradedUser.getInventory().getList());
                        theirUsername.setText(tradedUser.getUsername());
                        tradeduserAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        tmpInventory.clear();
                        theirUsername.setText("");
                        tradeduserAdapter.notifyDataSetChanged();
                    }

                });
            }
        }
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_trade;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_trade;
    }
}
