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


public class HelperFunctions {

    public static User getSingleUserGeneral(Context context){
        int offlineQueueExists = getOfflineChangesQueued(context);
        if (offlineQueueExists == 1){
            offlineQueueExists = tryToProcessOfflineQueue(context);
        }

        User user;

        if (offlineQueueExists == 0){ // nothing queued, can communicate with db
            String jestID = getJestID(context);

            if (jestID.equals("defaultvalue")) {
                Log.i("Error", "Did not find correct jestID");
                return null;
            }
            user = getSingleUserByJestID(context, jestID);
            if (user != null){
                return user;
            }
            else{
                triggerOfflineChangesQueued(context);
                offlineQueueExists = getOfflineChangesQueued(context);
            }
        }

        if (offlineQueueExists == 1){
            user = getSingleUserOffline(context);
            if (user != null){
                return user;
            }
            else{
                throw new NullPointerException("null user received from file");
            }
        }
        return null;

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
                Log.i("Error", "user is: " + user);
                return !user.getUsername().equals("fake name") ? user : null;
//                return user;
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
//            triggerOfflineChangesQueued(context); // only getting, not triggered?
//            offlineQueueExists = getOfflineChangesQueued(context);
            return users.get(0);
        }
        else{
            return null;
        }
    }

    public static ArrayList<User> getUsersGeneral(Context context){


        ArrayList<User> users;

        String query = "{" +
                "  \"query\": {" +
                "    \"match_all\": {}" +
                "    }" +
                "}";

        users = getAllUsers(query);
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
        getUsersTask.execute();

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
    public static void triggerOfflineChangesQueued(Context context){
//        SharedPreferences settings = context.getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.putInt("offlineChanges", 1);
//        editor.commit();
        setOfflineChangesQueued(context, 1);
    }

    public static void setOfflineChangesQueued(Context context, int val){
        SharedPreferences settings = context.getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("offlineChanges", val);
        editor.commit();
    }

    public static int getOfflineChangesQueued(Context context){
        SharedPreferences settings = context.getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        return settings.getInt("offlineChanges", -1);
    }

    public static ArrayList<User> syncCurrentToList(Context context, User user, ArrayList<User> users){
        if (users != null){
            String jid = user.getId(); // username also works

            for (int i = 0; i < users.size(); i++){
                if (jid.equals(users.get(i).getId())){
                    users.remove(i);
                    users.add(user);
                    break;
                }
            }

        }

        return users;
    }

    public static void updateSingleUser(Context context, User user){
        // update in db

        int offlineQueueExists = getOfflineChangesQueued(context);
        if (offlineQueueExists == 1){
            offlineQueueExists = tryToProcessOfflineQueue(context);
        }

        if (offlineQueueExists == 0){
            ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
            updateUserTask.execute(user);
            try{
                int status = updateUserTask.get();
                if (status < 0){
                    triggerOfflineChangesQueued(context);
                }
            }
            catch (Exception e){
                Log.i("Error", "Failed update user from the async object\n" + e.toString() +"\n.");
            }

        }
        saveCurrentUser(context, user);
    }

    public static void updateUsers(Context context, ArrayList<User> users){
        // make sure to call whenever updating a single user too
        // update in db
//        int offlineQueueExists = getOfflineChangesQueued(context);
//        if (offlineQueueExists == 1){
//            offlineQueueExists = tryToProcessOfflineQueue(context);
//        }
        if (users != null){
            int status = 2;
            ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
            for (int i = 0; i< users.size(); i++){
                updateUserTask.execute(users.get(i));
                try{
                    int n = updateUserTask.get();
                    status = Math.min(status, n);
                }
                catch (Exception e){
                    Log.i("Error", "Failed update user from the async object\n" + e.toString() +"\n.");
                }
            }
            if (status < 0){
                triggerOfflineChangesQueued(context);
            }
            else if (status > 0){
                setOfflineChangesQueued(context, 0);
            }
            // clear and save in file
            clearFile(context, "allUsersFile.txt");
            saveInFile(users, context, "allUsersFile.txt");
        }

    }

    public static int tryToProcessOfflineQueue(Context context){
        // load from files
        // sync
        // try to push to db
        // write to files
        User currentlylogged;
        ArrayList<User> cur = loadFromFile(context, "currentUserFile.txt");
        if (cur.size()>0){
            currentlylogged = cur.get(0);
            ArrayList<User> users = loadFromFile(context, "allUsers.txt");
            users = syncCurrentToList(context, currentlylogged, users);
            if (users != null){
                updateUsers(context, users);
            }
            saveCurrentUser(context, currentlylogged);
        }
        return getOfflineChangesQueued(context);
    }

    private static final String FILENAME = "file.sav";

    /**
     * Loads an ArrayList of the given type from a file
     */
    public static ArrayList<User> loadFromFile(Context context, String filename) {
        ArrayList<User> data;
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            // add dependency: File > Project Structure > app > Dependencies > + > dependency
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<User>>(){}.getType();
            data = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
//			e.printStackTrace();
            data = new ArrayList<User>();
        }

        return data;
    }

    public static void saveCurrentUser(Context context, User user){
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        clearFile(context, "currentUserFile.txt");
        saveInFile(userList, context, "currentUserFile.txt");
        String query = "{" +
                "  \"query\": {" +
                "    \"match_all\": {}" +
                "    }" +
                "}";
        syncCurrentToList(context, user, getAllUsers(query));
    }
    /**
     *
     * Saves the current ArrayList data to a file
     *
     */
    public static void saveInFile(ArrayList<User> data, Context context, String filename) {
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

}


