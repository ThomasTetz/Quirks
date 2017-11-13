package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        jestID = settings.getString("jestID", "defaultvalue");
        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        applyButton = (Button) findViewById(R.id.applyFilterButton);
        //updateQuirkList(jestID);
        final User currentlylogged = HelperFunctions.getUserObject(jestID);
        quirkList = currentlylogged.getQuirks();

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

        // Creating adapter for spinner, and attach adapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        applyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String query = "";
//                String extraString = "";
                if (String.valueOf(spinner.getSelectedItem()).equals("All Habits")){
                    query = getQueryFilterAll();
                }
                else if(String.valueOf(spinner.getSelectedItem()).equals("Today\'s Habits")){
                    query = getQueryFilterToday();
                }
//                Toast.makeText(QuirksActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner  : "+ String.valueOf(spinner.getSelectedItem()) + " " +extraString,
//                        Toast.LENGTH_SHORT).show();
                if (query.equals("")){
                    Log.i("Error", "Failed to get query based on spinner selection");
                }
                else{
//                    applyFilter(query);

                    offlineFilter(query, currentlylogged);

                }
            }

        });

        // Create listView handler (for custom listview important that all items must have focusable = false)
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(QuirksActivity.this, EditQuirkActivity.class);
                intent.putExtra("SELECTED_QUIRK_INDEX", position);
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

    public void updateQuirkList(String jestID){
        User currentlylogged = HelperFunctions.getUserObject(jestID);
        QuirkList tempList = currentlylogged.getQuirks();
        quirkList.clearAndAddQuirks(tempList);
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

    public void offlineFilter(String query, User user){
        QuirkList userQuirks = user.getQuirks();
        QuirkList filteredQuirks = new QuirkList();
        int size = user.getQuirks().size();


        if (query.equals("all")){
            // show all
            // maybe remove the conditions that default blank argument to showing all values
//            filteredQuirks = userQuirks;
            updateQuirkList(jestID);
            adapter.notifyDataSetChanged();
//            applyOfflineFilter(quirkList);
        }
        else if (query.equals("today")){
            // show all today
            for (int i = 0; i < size; i++){
                Quirk curQuirk = userQuirks.getQuirk(i);
                ArrayList<Day> occurences = curQuirk.getOccDate();
                // @TODO somehow get today
                Day today = Day.MONDAY;

//                LocalDate date = LocalDate.of(2014, 2, 15); // 2014-06-15
//                DayOfWeek dayOfWeek = date.getDayOfWeek();
//                int dayOfWeekIntValue = dayOfWeek.getValue(); // 6
//                String dayOfWeekName = dayOfWeek.name(); // SATURDAY
//
//
//                System.out.println(DayOfWeek.of(1).toString());
//                DateFormatSymbols.getInstance().getWeekdays();

                if (occurences.contains(today)){
                    filteredQuirks.addQuirk(curQuirk);
                }
            }
            applyOfflineFilter(filteredQuirks);
        }
        else{
            System.out.println("offline filter failed if/else statements");
        }
    }

    public void applyOfflineFilter(QuirkList filteredQuirks){
        System.out.println("the filtered quirks:");
        for (int i = 0; i < filteredQuirks.size(); i++){
            System.out.println("\t" + filteredQuirks.getQuirk(i).getType());
        }

        System.out.println("previously displayed quirks:");
        for (int i = 0; i < quirkList.size(); i++){
            System.out.println("\t" + quirkList.getQuirk(i).getType());
        }

        System.out.println("before clearAndAdd, passing size: " + filteredQuirks.size());
        quirkList.clearAndAddQuirks(filteredQuirks);
        System.out.println("after clearAndAdd, the passed now has size: " + filteredQuirks.size());

        System.out.println("currently displayed quirks:");
        for (int i = 0; i < quirkList.size(); i++){
            System.out.println("\t" + quirkList.getQuirk(i).getType());
        }
        System.out.println("before adapter");
        adapter.notifyDataSetChanged();
        System.out.println("after adapter");
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
