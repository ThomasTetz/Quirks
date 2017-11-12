package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import cmput301f17t12.quirks.Adapters.NewsItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Interfaces.Newsable;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class MainActivity extends BaseActivity {
    private ArrayList<Newsable> newsitems = new ArrayList<>();
    private NewsItemAdapter adapter;
    private User currentlylogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the user
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            if (extras.containsKey("user")){
                currentlylogged = (User) getIntent().getSerializableExtra("user");
                System.out.println("Main Activity as:\n\tUser object: " + currentlylogged + "\n\tusername: " + currentlylogged.getUsername());
            }
            else{
                System.out.println("Intent had extras but not user");
            }
        }
        else{
            System.out.println("Intent had no extras");

            String jestID = "AV-xx8ahi8-My2t7XP4j";

            ElasticSearchUserController.GetSingleUserTask getSingleUserTask
                    = new ElasticSearchUserController.GetSingleUserTask();
            getSingleUserTask.execute(jestID);

            try {
                currentlylogged = getSingleUserTask.get();

            }
            catch (Exception e) {
                Log.i("Error", "Failed to get the users from the async object");
                Log.i("Error", e.toString());
            }
        }

        ArrayList<Quirk> quirks = currentlylogged.getQuirks().getList();
        for (int i = 0; i < quirks.size(); i++) {
            newsitems.addAll(quirks.get(i).getEventList().getList());
        }
        newsitems.addAll(quirks);

        Collections.sort(newsitems, new Comparator<Newsable>() {
            public int compare(Newsable m1, Newsable m2) {
                return m2.getDate().compareTo(m1.getDate());
            }
        });

        // instantiate custom adapter
        adapter = new NewsItemAdapter(newsitems, this);

        // handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.newsfeed_listview);
        lView.setAdapter(adapter);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_home;
    }
}


