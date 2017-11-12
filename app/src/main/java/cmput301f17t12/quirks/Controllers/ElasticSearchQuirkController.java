package cmput301f17t12.quirks.Controllers;

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



import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t12.quirks.Models.Quirk;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


/**
 * Created by thomas on 2017-11-11.
 */

public class ElasticSearchQuirkController {

    private static JestDroidClient client;

    // TODO we need a function which adds quirks to elastic search
    public static class AddQuirksTask extends AsyncTask<Quirk, Void, Void> {

        @Override
        protected Void doInBackground(Quirk... quirks) {
            verifySettings();

            for (Quirk quirk : quirks) {
//                GorupName_ProjectName
                Index index = new Index.Builder(quirk).index("cmput301f17t12_quirks").type("quirk").build();
//                Index index = new Index.Builder(quirk).index().type().build();

                try {
                    // where is the client?
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded())
                    {
                        quirk.setId(result.getId());
                    }
                    else
                    {
                        Log.i("Error", "Elasticsearch was not able to add the quirk");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the quirks");
                }

            }
            return null;
        }
    }

    // TODO we need a function which gets quirks from elastic search
    public static class GetQuirksTask extends AsyncTask<String, Void, ArrayList<Quirk>> {
        @Override
        protected ArrayList<Quirk> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Quirk> quirks = new ArrayList<Quirk>();

            // TODO Build the query
            Search search = new Search.Builder(search_parameters[0])
                    .addIndex("testing")
                    .addType("quirk")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if(result.isSucceeded()) {
                    List<Quirk> foundQuirks = result.getSourceAsObjectList(Quirk.class);
                    quirks.addAll(foundQuirks);
                }
            }
            catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }

            return quirks;
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
