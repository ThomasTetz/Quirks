package cmput301f17t12.quirks.Controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t12.quirks.Models.User;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by thomas on 2017-11-11.
 */

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
