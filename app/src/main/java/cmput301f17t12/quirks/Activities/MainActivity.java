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
import java.util.Date;

import cmput301f17t12.quirks.Adapters.NewsItemAdapter;
import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Interfaces.Newsable;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class MainActivity extends BaseActivity {
    private ArrayList<Newsable> newsitems = new ArrayList<>();
    private NewsItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        // TODO: Get current user's newsable items.
        // Should be current user's + friends', but that is for part 5.

        // get the user
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            if (extras.containsKey("user")){
                User user = (User) getIntent().getSerializableExtra("user");
                System.out.println("Main Activity as:\n\tUser object: " + user + "\n\tusername: " + user.getUsername());
            }
            else{
                System.out.println("Intent had extras but not user");
            }
        }
        else{
            System.out.println("Intent had no extras");
        }


        // Test
        ArrayList<Day> occurence = new ArrayList<Day>() {};
        occurence.add(Day.SUNDAY);
        Date thirtyminsago = new Date(System.currentTimeMillis() - 3600 * 500);
        Quirk testQuirk = new Quirk("Eat a fruit every sunday", "Eating", thirtyminsago, occurence, 10);
        testQuirk.setUser("zafra");
        Event testEvent = new Event(testQuirk, "This is an event haha nice dude", new Date());

        newsitems.add(testQuirk);
        newsitems.add(testEvent);


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


