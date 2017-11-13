package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import cmput301f17t12.quirks.*;
import cmput301f17t12.quirks.Adapters.QuirkListItemAdapter;
import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Helpers.HelperFunctions;

import cmput301f17t12.quirks.Models.*;

public class QuirksActivity extends BaseActivity {
    private QuirkList quirkList = new QuirkList();
    private Date dateFilter;
    private QuirkListItemAdapter adapter;
    private String jestID; //TODO: Change this with shared preferences
    private Spinner spinner;
    private Button applyButton;
    private EditText filterValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_quirks);

        //TODO: Get user's quirklist

        // Testing - replace this with a userID search later on.
        jestID = "AV-xx8ahi8-My2t7XP4j";

        applyButton = (Button) findViewById(R.id.applyFilterButton);
        filterValue = (EditText) findViewById(R.id.filterVal);

        //updateQuirkList(jestID);
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

        // Spinner element
        spinner = (Spinner) findViewById(R.id.filter_type);

        // TODO: Handle spinner click
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Habits");
        categories.add("Today\'s Habits");
        categories.add("By Type");
        categories.add("By Comment");

        // Creating adapter for spinner, and attach adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query = "";
                String extraString = "";
                if (String.valueOf(spinner.getSelectedItem()).equals("All Habits")){
                    query = getQueryFilterAll();
                }
                else if(String.valueOf(spinner.getSelectedItem()).equals("Today\'s Habits")){
                    query = getQueryFilterToday();
                }
                else if(String.valueOf(spinner.getSelectedItem()).equals("By Type")){
                    query = getQueryFilterType();
                    extraString = filterValue.getText().toString();
                }
                else if(String.valueOf(spinner.getSelectedItem()).equals("By Comment")){
                    query = getQueryFilterComment();
                    extraString = filterValue.getText().toString();
                }
                Toast.makeText(QuirksActivity.this,
                        "OnClickListener : " +
                                "\nSpinner  : "+ String.valueOf(spinner.getSelectedItem()) + " " +extraString,
                        Toast.LENGTH_SHORT).show();
                if (query.equals("")){
                    Log.i("Error", "Failed to get query based on spinner selection");
                }
                else{
                    applyFilter(query);
                }
            }

        });

        // Create listView handler (important all elements with a custom listview item must have focusable = false)
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(QuirksActivity.this, EditQuirkClickedActivity.class);
                Quirk quirk = (Quirk) parent.getItemAtPosition(position);
                intent.putExtra("Quirk", quirk);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateQuirkList(jestID);
        adapter.notifyDataSetChanged();
    }

    public String getQueryFilterAll(){
        String query = "all";
        // for the user
        // look in quirklist
        // match all
        return query;


    }

    public String getQueryFilterToday(){
        String query = "today";
        // for the user
        // look in quirklist
        // match where Day today is within occDate
        return query;
    }

    public String getQueryFilterType(){
        String query = "type";
        // for the user
        // look in quirklist
        // match where type is type
        return query;
    }

    public String getQueryFilterComment(){
        String query = "comment";
        // for the user
        // look in quirklist
        // match where comment contains the word
        return query;
    }

    public void applyFilter(String query){

        ElasticSearchUserController.GetQuirksTask getQuirksTask
                = new ElasticSearchUserController.GetQuirksTask();
        getQuirksTask.execute(query, jestID);

        try {
            ArrayList<Quirk> quirks = getQuirksTask.get();

            System.out.println("size: " + quirks.size());
            for (int i=0; i< quirks.size(); i++){
                System.out.println(quirks.get(i).getType());
            }

        }
        catch (Exception e) {
            Log.i("Error", "Failed to get filtered quirks from the async object");
            Log.i("Error", e.toString());
        }

    }

    public void filterTest(){
//        User user = new User("Test1", );
    }

    public void updateQuirkList(String jestID){
        User currentlylogged = HelperFunctions.getUserObject(jestID);
        QuirkList tempList = currentlylogged.getQuirks();
        Log.d("DEBUG", "Got into here");
        quirkList.clearAndAddQuirks(tempList);
    }


    @Override
    int getContentViewId() { return R.layout.activity_quirks; }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_quirklist;
    }

    // TODO:
    // Update the view -> Show quirks that fit the filter
    public void setFilterClicked(){

    }

}
