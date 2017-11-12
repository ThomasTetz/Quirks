package cmput301f17t12.quirks.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.R;

/**
 * Created by charleshoang on 2017-11-12.
 */

public class EventListActivity extends AppCompatActivity {


    private ImageView ivTopBorder;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        ivTopBorder = (ImageView) findViewById(R.id.el_topborder);
        btnBack = (ImageButton)findViewById(R.id.el_btnBack);

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


        //Test events for quirk
        ArrayList<Day> occurence = new ArrayList<Day>() {};
        occurence.add(Day.SUNDAY);
        Date thirtyminsago = new Date(System.currentTimeMillis() - 3600 * 500);
        Quirk testQuirk = new Quirk("Boy dance", "dancing", thirtyminsago, occurence, 10);
        testQuirk.setUser("jo");
        Event testEvent = new Event(testQuirk, "Event1", new Date());


    }



}
