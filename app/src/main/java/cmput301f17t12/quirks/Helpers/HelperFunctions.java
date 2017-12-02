package cmput301f17t12.quirks.Helpers;

import android.util.Log;

import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Models.User;

public class HelperFunctions {

    public static User getUserObject(String jestID) {
        ElasticSearchUserController.GetSingleUserTask getSingleUserTask
                = new ElasticSearchUserController.GetSingleUserTask();
        getSingleUserTask.execute(jestID);

        try {
            return getSingleUserTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

    public static ArrayList<User> getAllUsers(String query) {
        ElasticSearchUserController.GetUsersTask getUsersTask
                = new ElasticSearchUserController.GetUsersTask();
        getUsersTask.execute(query);

        try {
            return getUsersTask.get();
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

    public static User getSingleUser(String username) {
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

        try {
            return getUsersTask.get().get(0);
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get the users from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

}

