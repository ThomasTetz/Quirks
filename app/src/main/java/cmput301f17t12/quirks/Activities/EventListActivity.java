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
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ivTopBorder = (ImageView) findViewById(R.id.el_topborder);
        btnBack = (ImageButton)findViewById(R.id.el_btnBack);
        tvQuirkName = (TextView) findViewById(R.id.el_quirkname);

        ivTopBorder.setBackgroundColor(0x8B999D);
        btnBack.setBackgroundColor(0x8B999D);

        /**
         * When button back is clicked, return to view activity.
         */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });


        //Testing variables
        ArrayList<Day> occurence = new ArrayList<Day>() {};
        occurence.add(Day.SUNDAY);
        Date thirtyminsago = new Date(System.currentTimeMillis() - 3600 * 500);
        Quirk testQuirk = new Quirk("Boy dance", "dancing", thirtyminsago, occurence, 10);
        testQuirk.setUser("jo");
        Event testEvent = new Event(testQuirk, "Event0", new Date());
        Event testEvent1 = new Event(testQuirk, "Event1", new Date());
        Event testEvent2 = new Event(testQuirk, "Event2", new Date());
        Event testEvent3 = new Event(testQuirk, "Event3", new Date());
        Event testEvent4 = new Event(testQuirk, "Event4", new Date());
        Event testEvent5 = new Event(testQuirk, "Event5", new Date());
        Event testEvent6 = new Event(testQuirk, "Event6", new Date());
        Event testEvent7 = new Event(testQuirk, "Event7", new Date());
        Event testEvent8 = new Event(testQuirk, "Event8", new Date());
        Event testEvent9 = new Event(testQuirk, "Event9", new Date());

        eventList.addEvent(testEvent);
        eventList.addEvent(testEvent1);
        eventList.addEvent(testEvent2);
        eventList.addEvent(testEvent3);
        eventList.addEvent(testEvent4);
        eventList.addEvent(testEvent5);
        eventList.addEvent(testEvent6);
        eventList.addEvent(testEvent7);
        eventList.addEvent(testEvent8);
        eventList.addEvent(testEvent9);


        tvQuirkName.setText(testQuirk.getTitle());
        adapter = new EventListItemAdapter(eventList, this);
        ListView lView = (ListView) findViewById(R.id.el_eventslistview);
        lView.setAdapter(adapter);




    }



}
