package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Enumerations.Rarity;
import cmput301f17t12.quirks.Models.Drop;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class FakeLoginActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button searchButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_login);

        usernameText = findViewById(R.id.usernameText);
        searchButton = findViewById(R.id.searchUserButton);
        addButton = findViewById(R.id.addUserButton);
        final Drop drop = new Drop(Rarity.COMMON, "pebble");
// tweet example:
//{
//    "query" : {
//            "wildcard" : {
//                "message" : param
//            }
//    }
//    sort : {
//            "date" : {
//                "order" : "desc"
//            }
//    }
//    "size" : 10"" +
//};

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String username = usernameText.getText().toString();
                // new user
//                NormalTweet newTweet = new NormalTweet(text);
                Inventory inventory = new Inventory();
//                Drop drop = new Drop(Rarity.COMMON, "pebble");
                inventory.addDrop(drop);
                User user = new User(username, inventory, new ArrayList<User>(), new QuirkList());
                System.out.println(user.getInventory().hasDrop(drop));
                user.getInventory().printItems();
//                tweetList.add(newTweet);
//                adapter.notifyDataSetChanged();

                ElasticSearchUserController.AddUsersTask addUsersTask
                        = new ElasticSearchUserController.AddUsersTask();
                addUsersTask.execute(user);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String param = usernameText.getText().toString();
//                String query = "{\n" +
//                        "	\"query\" : { \"wildcard\" : { \"message\" : \"" + param + "\" } },\n" +
//                        "	\"sort\" : { \"date\" : { \"order\" : \"desc\" } },\n" +
//                        "	\"size\" : 10\n" +
//                        "}";
                String query1 = "{" +
                                "  \"query\": {" +
                                "    \"username\": \"" + param + "\"" +
                                "  }" +
                                "}";

                String query2 = "{" +
                        "  \"query\": {" +
                        "    \"wildcard\": {" +
                        "      \"username\": \"" + param + "\"" +
                        "    }" +
                        "  }" +
                        "}";


                String query3 = "{" +
                        "  \"query\": {" +
                        "      \"match_all\": {}" +
                        "  }" +
                        "}";

                String query4 = "{" +
                        "  \"query\": {" +
                        "    \"match\": {" +
                        "      \"username\": \"" + param + "\"" +
                        "    }" +
                        "  }" +
                        "}";

                ElasticSearchUserController.GetUsersTask getUsersTask
                        = new ElasticSearchUserController.GetUsersTask();
                getUsersTask.execute(query4);
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;



                try {
//                    CharSequence text = "Hello toast!";
//                    CharSequence text = getUsersTask.get();

                    Toast toast = Toast.makeText(context, "hi", duration);
                    toast.show();

                    ArrayList<User> users = getUsersTask.get();

                    System.out.println("size: " + users.size());
//                    Drop drop = new Drop(Rarity.COMMON, "pebble");
                    for(int i=0; i<users.size(); i++){
                        System.out.println(users.get(i).getUsername());
                        System.out.println("  "+users.get(i).getInventory().hasDrop(drop));
                        users.get(i).getInventory().printItems();
                    }

//                    tweetList.clear();
//                    tweetList.addAll(getUsersTask.get());
                }
                catch (Exception e) {
                    Log.i("Error", "Failed to get the tweets from the async object");
                    String text = "Failed to get the tweets from the async object";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

//                adapter.notifyDataSetChanged();
            }
        });





    }
}
