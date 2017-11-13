package cmput301f17t12.quirks.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Adapters.EventListItemAdapter;
import cmput301f17t12.quirks.Adapters.QuirkListItemAdapter;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

/**
 * Created by charleshoang on 2017-11-12.
 */

public class EventListActivity extends AppCompatActivity {

    private ImageView ivTopBorder;
    private ImageButton btnBack;
    private TextView tvQuirkName;
    private EventListItemAdapter adapter;
    private EventList eventList = new EventList();
    private Integer pos;
    private Quirk quirk;
    private String jestID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ivTopBorder = (ImageView) findViewById(R.id.el_topborder);
        btnBack = (ImageButton)findViewById(R.id.el_btnBack);
        tvQuirkName = (TextView) findViewById(R.id.el_quirkname);
        ivTopBorder.setBackgroundColor(0x8B999D);
        btnBack.setBackgroundColor(0x8B999D);

        //Test User - TODO: replace this with actual userID
        jestID = "AV-xx8ahi8-My2t7XP4j";

        // Use the quirk's pos to get the Eventlist
        Integer pos = (Integer) getIntent().getSerializableExtra("quirkPos");
        User currentlylogged = HelperFunctions.getUserObject(jestID);
        quirk = currentlylogged.getQuirks().getQuirk(pos);
        eventList = quirk.getEventList();

        /*
        //Testing variables
        ArrayList<Day> occurence = new ArrayList<Day>() {};
        occurence.add(Day.SUNDAY);
        Date thirtyminsago = new Date(System.currentTimeMillis() - 3600 * 500);
        Quirk testQuirk = new Quirk("EVENT LIST TEST", "test", thirtyminsago, occurence, 10, jestID);

        EventList testEventList = new EventList();

        testEventList.addEvent(new Event(jestID, "Event0", new Date()));
        testEventList.addEvent(new Event(jestID, "Event1", new Date()));
        testEventList.addEvent(new Event(jestID, "Event2", new Date()));
        testEventList.addEvent(new Event(jestID, "Event3", new Date()));
        testEventList.addEvent(new Event(jestID, "Event4", new Date()));
        testEventList.addEvent(new Event(jestID, "Event5", new Date()));
        testEventList.addEvent(new Event(jestID, "Event6", new Date()));
        testEventList.addEvent(new Event(jestID, "Event7", new Date()));
        */

        tvQuirkName.setText(quirk.getTitle());
        adapter = new EventListItemAdapter(eventList, this);
        ListView lView = (ListView) findViewById(R.id.el_eventslistview);
        lView.setAdapter(adapter);

        /**
         * When button back is clicked, return to view activity.
         */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

    }

    // Called if activity resumes. Loads any event updates from the database
    @Override
    protected void onResume(){
        super.onResume();
        updateEventList(jestID);
        adapter.notifyDataSetChanged();
    }

    // Grabs the latest events from the db
    public void updateEventList(String jestID){
        User currentlylogged = HelperFunctions.getUserObject(jestID);
        quirk = currentlylogged.getQuirks().getQuirk(pos);
        EventList tempEventList = currentlylogged.getQuirks().getQuirk(pos).getEventList();
        eventList.clearAndAddEvents(tempEventList);
    }



}
