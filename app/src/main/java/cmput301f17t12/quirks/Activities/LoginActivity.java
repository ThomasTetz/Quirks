package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Enumerations.Rarity;
import cmput301f17t12.quirks.Models.Drop;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class LoginActivity extends AppCompatActivity {
    private String userName;

    private EditText loginText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginText = findViewById(R.id.loginUser);
        loginButton = findViewById(R.id.loginBtn);


        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String username = loginText.getText().toString();

                String query = "{" +
                        "  \"query\": {" +
                        "    \"match\": {" +
                        "      \"username\": \"" + username + "\"" +
                        "    }" +
                        "  }" +
                        "}";






                ElasticSearchUserController.GetUsersTask getUsersTask
                        = new ElasticSearchUserController.GetUsersTask();
                getUsersTask.execute(query);


//                Context context = getApplicationContext();
//                int duration = Toast.LENGTH_SHORT;



                try {
//                    CharSequence text = "Hello toast!";
//                    CharSequence text = getUsersTask.get();

//                    Toast toast = Toast.makeText(context, "hi", duration);
//                    toast.show();

                    ArrayList<User> users = getUsersTask.get();

                    System.out.println("size: " + users.size());
                    if (users.size() == 1){
                        System.out.println("\n\nvvv\nalready registered\n^^^\n\n");
//                        testUpdate1(users.get(0));
                        loginUser(users.get(0));
                    }
                    else if (users.size() > 1){
                        Log.i("Error", "Username appears more than once in the database");
                    }
                    else{
                        registerUser(username);
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "Failed to get the users from the async object");
                    Log.i("Error", e.toString());
//                    String text = "Failed to get the tweets from the async object";
//                    Toast toast = Toast.makeText(context, text, duration);
//                    toast.show();
                }

//                adapter.notifyDataSetChanged();
            }
        });
    }

    private void loginUser(User user){
        // after elasticsearch, go to main as that user
        System.out.println("Logging in as: " + user.getUsername());
        System.out.println("JestId: " + user.getId());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
//        startActivity(new Intent(this, MainActivity.class));
    }

    //Need to check, is signing up new users in the spec??
    private void registerUser(String username){
        // create new user
        User user = new User(username, new Inventory(), new ArrayList<User>(), new QuirkList());

        ElasticSearchUserController.AddUsersTask addUsersTask
                = new ElasticSearchUserController.AddUsersTask();
        addUsersTask.execute(user);
        System.out.println("Successfully added new user");
        loginUser(user);
    }

//    private void testUpdate1(User user1){
//        System.out.println("Testing update user");
//
//        String query2 = "AV-uhlzji8-My2t7XPu9";
//        query2 = user1.getId();
//        ElasticSearchUserController.GetSingleUserTask getSingleUserTask
//                = new ElasticSearchUserController.GetSingleUserTask();
//        getSingleUserTask.execute(query2);
//        User user;
//        try{
//            user = getSingleUserTask.get();
//
//            System.out.println("got single: " + user.getUsername() + "\ninventory:");
//            user.getInventory().printItems();
//            testUpdate2(user);
//        }
//        catch(Exception e){
//            Log.i("Error", "Failed to get the user by id");
//            Log.i("Error", e.toString());
//        }
//
//
//
//    }
//    private void testUpdate2(User user){
//        // update
//        user.getInventory().addDrop(new Drop(Rarity.UNCOMMON, "frog"));
////        while(user.getInventory().hasDrop(new Drop(Rarity.UNCOMMON, "frog"))){
////            user.getInventory().removeDrop(new Drop(Rarity.UNCOMMON, "frog"));
////        }
//
////        user.getUsername();
//        ElasticSearchUserController.UpdateUserTask updateUserTask
//                = new ElasticSearchUserController.UpdateUserTask();
//        updateUserTask.execute(user);
//
//        // get them again
//
//        String query2 = "AV-uhlzji8-My2t7XPu9";
//        query2 = user.getId();
//        ElasticSearchUserController.GetSingleUserTask getSingleUserTask
//                = new ElasticSearchUserController.GetSingleUserTask();
//        getSingleUserTask.execute(query2);
//
//
//        try{
//            user = getSingleUserTask.get();
//
//            System.out.println("got single after update: " + user.getUsername() + "\ninventory:");
//            user.getInventory().printItems();
////            testUpdate2(user);
//        }
//        catch(Exception e){
//            Log.i("Error", "Failed to get the user by id");
//            Log.i("Error", e.toString());
//        }
//    }
}
