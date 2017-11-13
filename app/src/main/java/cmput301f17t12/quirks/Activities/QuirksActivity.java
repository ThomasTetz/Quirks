package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import cmput301f17t12.quirks.*;
import cmput301f17t12.quirks.Adapters.QuirkListItemAdapter;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Helpers.HelperFunctions;

import cmput301f17t12.quirks.Models.*;

public class QuirksActivity extends BaseActivity {
    private QuirkList quirkList = new QuirkList();
    private Date dateFilter;
    private QuirkListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quirks);

        //TODO: Get user's quirklist

        // Testing - replace this with a userID search later on.
        String jestID = "AV-xx8ahi8-My2t7XP4j";

        User currentlylogged = HelperFunctions.getUserObject(jestID);
        quirkList = currentlylogged.getQuirks();
        // Test the ListView format
      /*  ArrayList testOccurence = new ArrayList();
        testOccurence.add(Day.MONDAY);
        Date testTime = new Date(System.currentTimeMillis());
        Quirk testQuirk = new Quirk("Big ol test", "TEST", testTime, testOccurence, 3,"john");

        testQuirk.setUser("jlane");
        testQuirk.incCurrValue();
        quirkList.addQuirk(testQuirk);

        ArrayList testOccurence2 = new ArrayList();
        testOccurence2.add(Day.MONDAY);
        Date testTime2 = new Date(System.currentTimeMillis());

        Quirk testQuirk2 = new Quirk("Big ol test", "TEST", testTime2, testOccurence2, 2,"david");
        testQuirk2.setUser("jlane");
        testQuirk2.incCurrValue();
        quirkList.addQuirk(testQuirk2);
        */


        //TODO: Create listView object and assign the custom adapter
        // create instance of custom adapter
        adapter = new QuirkListItemAdapter(quirkList, this);

        // create listView and assign custom adapter
        ListView lView = (ListView) findViewById(R.id.quirk_listview);
        lView.setAdapter(adapter);

        Button QuirkCreate = (Button)findViewById(R.id.add_quirk_button);
        QuirkCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuirksActivity.this,AddQuirkActivity.class);
                intent.putExtra("Editing",1);
                startActivityForResult(intent,1);
            }
        });
    }

    public void filterTest(){
//        User user = new User("Test1", );
    }

    @Override
    int getContentViewId() { return R.layout.activity_quirks; }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_quirklist;
    }

    // TODO:
    // Add a new quirk -> Call new add quirk activity
    public void addQuirkClicked(){

    }

    // TODO:
    // Edit an existing quirk -> Call new edit quirk activity
    public void editQuirkClicked(int position){
    }

    // TODO:
    // Get the events -> ? (Go to event activity??)
    public void getQuirkEventsClicked(){
    }

    // TODO:
    // Update the view -> Show quirks that fit the filter
    public void setFilterClicked(){

    }

}
