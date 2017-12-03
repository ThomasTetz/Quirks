package cmput301f17t12.quirks.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Models.User;




        import android.content.Context;
        import android.content.SharedPreferences;
        import android.util.Log;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.io.BufferedReader;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.lang.reflect.Type;
        import java.util.ArrayList;

        import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
        import cmput301f17t12.quirks.Models.User;

public class HelperFunctions {

    public static User getSingleUserGeneral(Context context){

        User user;
        String jestID = getJestID(context);

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
            user = null;
        }
        else{
            user = getSingleUserByJestID(context, jestID);
        }

        if (user != null){
            return user;
        }
        else{
            user = getSingleUserOffline(context);
            if (user != null){
                return user;
            }
            else{
                throw new NullPointerException("null user received from file");
            }
        }
    }

    public static String getJestID(Context context){
        SharedPreferences settings = context.getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        return settings.getString("jestID", "defaultvalue");
    }

    public static User getSingleUserByJestID(Context context, String jestID){
        User user = getSingleUserObject(jestID);
        return user;
    }

    public static User getSingleUserObject(String jestID) {
        ElasticSearchUserController.GetSingleUserTask getSingleUserTask
                = new ElasticSearchUserController.GetSingleUserTask();
        getSingleUserTask.execute(jestID);

        try {
            User user = getSingleUserTask.get();
            if (user != null){
                return user;
            }
            else{
                throw new NullPointerException("null return from elasticsearch controller");
            }
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get single user from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

    public static User getSingleUserOffline(Context context){
        ArrayList<User> users = loadFromFile(context, "currentUserFile.txt");
        if (users.size() > 0){
            return users.get(0);
        }
        else{
            return null;
        }
    }

    public static ArrayList<User> getUsersGeneral(Context context){


        ArrayList<User> users;


        users = getUsersObject();
        if (users != null){
            return users;
        }
        else{
            users = getUsersOffline(context);
            if (users != null){
                return users;
            }
            else{
                throw new NullPointerException("null list of users received from file");
            }
        }
    }

    public static ArrayList<User> getUsersObject() {
        ElasticSearchUserController.GetUsersTask getUsersTask
                = new ElasticSearchUserController.GetUsersTask();

        try {
            ArrayList<User> users = getUsersTask.get();
            if (users != null){
                return users;
            }
            else{
                throw new NullPointerException("null return from elasticsearch controller");
            }
        }
        catch (Exception e) {
            Log.i("Error", "Failed to get user list from the async object");
            Log.i("Error", e.toString());
        }
        return null;
    }

    public static ArrayList<User> getUsersOffline(Context context){
        ArrayList<User> users = loadFromFile(context, "allUsers.txt");
        if (users.size() > 0){
            return users;
        }
        else{
            return null;
        }
    }

    private static final String FILENAME = "file.sav";

    /**
     * Loads an ArrayList of the given type from a file
     */
    public static <E> ArrayList<E> loadFromFile(Context context, String filename) {
        ArrayList<E> data;
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            // add dependency: File > Project Structure > app < Dependencies < + < dependency
            Gson gson = new Gson();
            Type listType = new TypeToken<E>(){}.getType();
            data = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
//			e.printStackTrace();
            data = new ArrayList<E>();
        }

        return data;
    }

    public static void saveCurrentUser(Context context, User user){
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        saveInFile(userList, context, "currentUserFile.txt");
    }
    /**
     *
     * Saves the current ArrayList data to a file
     *
     */
    public static <E> void saveInFile(ArrayList<E> data, Context context, String filename) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(data, writer);
            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
//			e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     *
     * Clears data in a file.
     *
     */
    public static void clearFile(Context context, String filename){
        context.deleteFile(filename);
    }


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


