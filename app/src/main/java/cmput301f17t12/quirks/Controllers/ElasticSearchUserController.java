package cmput301f17t12.quirks.Controllers;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class ElasticSearchUserController {

    private static JestDroidClient client;
    private static String indexString = "cmput301f17t12_quirks";
    private static String typeString = "users";

    // TODO we need a function which adds users to elastic search
    public static class AddUsersTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();

            for (User user : users) {
//                GorupName_ProjectName
                Index index = new Index.Builder(user).index(indexString).type(typeString).build();
//                Index index = new Index.Builder(user).index().type().build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded())
                    {
                        user.setId(result.getId());
                        System.out.println("added user: " + user.getUsername());
                        System.out.println("id: " + user.getId());
                    }
                    else
                    {
                        Log.i("Error", "Elasticsearch was not able to add the user");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the users");
                }

            }
            return null;
        }
    }


    public static class UpdateUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            System.out.println("Trying to update: " + users[0].getUsername());
            Index index = new Index.Builder(users[0]).index(indexString).type(typeString).id(users[0].getId()).build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded())
                    {
//                        users[0].setId(result.getId());
                        System.out.println("updated user: " + users[0].getUsername());
//                        System.out.println("id: " + user.getId());
                    }
                    else
                    {
                        Log.i("Error", "Elasticsearch was not able to update the user");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the users");
                }

            return null;
        }
    }



    // TODO we need a function which gets users from elastic search
    public static class GetUsersTask extends AsyncTask<String, Void, ArrayList<User>> {
        @Override
        protected ArrayList<User> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<User> users = new ArrayList<User>();

            // TODO Build the query

            System.out.print("search_parameters[0]: ");
            System.out.println(search_parameters[0]);

            Search search = new Search.Builder(search_parameters[0])
                    .addIndex(indexString)
                    .addType(typeString)
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<User> foundUsers = result.getSourceAsObjectList(User.class);
                    users.addAll(foundUsers);
                    System.out.println("found size: " + foundUsers.size());
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                Log.i("Error", e.toString());
            }

            return users;
        }
    }

    // TODO we need a function which gets users from elastic search
    public static class GetQuirksTask extends AsyncTask<String, Void, ArrayList<Quirk>> {
        @Override
        protected ArrayList<Quirk> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Quirk> quirks = new ArrayList<Quirk>();


            // TODO Build the query

            System.out.print("search_parameters[0]: ");
            System.out.println(search_parameters[0]);
            System.out.println("Searching with param: " + search_parameters[0]);
            System.out.println("For user with JestID: " + search_parameters[1]);

            if (!search_parameters[1].equals("causeFailure")){
                return new ArrayList<Quirk>();

            }
            else{
                System.out.println("avoided failure");
            }

            Search search = new Search.Builder(search_parameters[0])
                    .addIndex(indexString)
                    .addType(typeString)
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<Quirk> foundQuirks = result.getSourceAsObjectList(Quirk.class);
                    quirks.addAll(foundQuirks);
                    System.out.println("found size: " + foundQuirks.size());
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                Log.i("Error", e.toString());
            }

            return quirks;
        }
    }

    // TODO we need a function which gets users from elastic search
    public static class GetSingleUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... username) {
            verifySettings();

//            User user = new User

            // TODO Build the query

            System.out.print("search_parameters[0]: ");
            System.out.println(username[0]);

            Get get = new Get.Builder(indexString, username[0]).type(typeString).build();



            try {
                JestResult result = client.execute(get);

                if(result.isSucceeded()) {
                    User user = result.getSourceAsObject(User.class);
                    System.out.println("found single user: " + user.getUsername());
                    return user;

                }
                else{
                    System.out.println("fail no error");
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                Log.i("Error", e.toString());
            }
            return new User("fake name", new Inventory(), new ArrayList<User>(), new QuirkList());
        }
    }

    public static class DeleteUserTask extends AsyncTask<User, Void, Void> {

        @Override
        protected Void doInBackground(User... users) {
            verifySettings();
            System.out.println("Trying to delete: " + users[0].getUsername());
//            Index index = new Index.Builder(users[0]).index(indexString).type(typeString).id(users[0].getId()).build();
            Delete delete = new Delete.Builder(users[0].getId()).index(indexString).type(typeString).build();

            try {
                // where is the client?
//                DocumentResult result = client.execute(index);
                DocumentResult result = client.execute(delete);
                if (result.isSucceeded())
                {
//                        users[0].setId(result.getId());
                    System.out.println("deleted user: " + users[0].getUsername());
//                        System.out.println("id: " + user.getId());
                }
                else
                {
                    Log.i("Error", "Elasticsearch was not able to delete the user");
                }
            }
            catch (Exception e) {
                Log.i("Error", "The application failed to build and send the users");
            }

            return null;
        }
    }


    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
