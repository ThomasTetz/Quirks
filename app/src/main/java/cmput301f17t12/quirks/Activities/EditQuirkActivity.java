package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

import cmput301f17t12.quirks.Controllers.ElasticSearchUserController;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Helpers.HelperFunctions;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

public class EditQuirkActivity extends AppCompatActivity {

    private ArrayList<Day> occurence;
    public CheckBox radButMon;
    public CheckBox radButTue;
    public CheckBox radButWed;
    public CheckBox radButThur;
    public CheckBox radButFri;
    public CheckBox radButSat;
    public CheckBox radButSun;
    public Quirk incomingQuirk;

    User currentlylogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quirk);

        radButMon = (CheckBox)findViewById(R.id.QuirkEditradioButtonMon);
        radButTue = (CheckBox)findViewById(R.id.QuirkEditradioButtonTue);
        radButWed = (CheckBox)findViewById(R.id.QuirkEditradioButtonWed);
        radButThur = (CheckBox)findViewById(R.id.QuirkEditradioButtonThur);
        radButFri = (CheckBox)findViewById(R.id.QuirkEditradioButtonFri);
        radButSat = (CheckBox)findViewById(R.id.QuirkEditradioButtonSat);
        radButSun = (CheckBox)findViewById(R.id.QuirkEditradioButtonSun);

        EditText TitleEdit = (EditText)findViewById(R.id.QuirkeditTextTitle);
        EditText TypeEdit = (EditText)findViewById(R.id.QuirkeditTextType);
        EditText ReasonEdit = (EditText)findViewById(R.id.QuirkeditTextReason);
        EditText GoalEdit = (EditText)findViewById(R.id.QuirkeditTextGoal);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }

        currentlylogged = HelperFunctions.getUserObject(jestID);

        Integer incomingQuirkIndex = getIntent().getIntExtra("SELECTED_QUIRK_INDEX", -1);

        if (incomingQuirkIndex == -1) {
            Log.i("Error", "Failed to receive SELECTED_QUIRK_INDEX");
            finish();
        }

        incomingQuirk = currentlylogged.getQuirks().getQuirk(incomingQuirkIndex);
        TitleEdit.setText(incomingQuirk.getTitle());
        TypeEdit.setText(incomingQuirk.getType());
        ReasonEdit.setText(incomingQuirk.getReason());
        GoalEdit.setText(String.valueOf(incomingQuirk.getGoalValue()));
        setOccurences();
    }

    public void saveButtonClicked(View v){
        String type = ((EditText)findViewById(R.id.QuirkeditTextType)).getText().toString();
        String title = ((EditText)findViewById(R.id.QuirkeditTextTitle)).getText().toString();
        String reason = ((EditText)findViewById(R.id.QuirkeditTextReason)).getText().toString();
        String goal = ((EditText)findViewById(R.id.QuirkeditTextGoal)).getText().toString();

        ArrayList<Day> occurences = occurenceItemSelected();

        if((type.equals(""))||(title.equals(""))||(goal.equals(""))){

        }

        else{
            incomingQuirk.setTitle(title);
            incomingQuirk.setType(type);
            incomingQuirk.setReason(reason);
            incomingQuirk.setOccDate(occurences);
            incomingQuirk.setGoalValue(Integer.parseInt(goal));

            ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
            updateUserTask.execute(currentlylogged);

            finish();
        }
    }

    public void cancelButtonClicked(View v){
        finish();
    }

    public void DeleteButtonClicked(View v) {
        currentlylogged.getQuirks().removeQuirk(incomingQuirk);
        ElasticSearchUserController.UpdateUserTask updateUserTask = new ElasticSearchUserController.UpdateUserTask();
        updateUserTask.execute(currentlylogged);
        finish();
    }

    private ArrayList<Day> occurenceItemSelected(){
        ArrayList<Day> Day = new ArrayList<Day>();
        if(radButMon.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.MONDAY);
        }
        if(radButTue.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.TUESDAY);
        }
        if(radButWed.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.WEDNESDAY);
        }
        if(radButThur.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.THURSDAY);
        }
        if(radButFri.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.FRIDAY);
        }
        if(radButSat.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.SATURDAY);
        }
        if(radButSun.isChecked()){
            Day.add(cmput301f17t12.quirks.Enumerations.Day.SUNDAY);
        }
        return Day;
    }

    private void setOccurences() {
        ArrayList<Day> occurence = incomingQuirk.getOccDate();

        if (occurence.contains(Day.MONDAY)) {
            radButMon.setChecked(true);
        }
        if (occurence.contains(Day.TUESDAY)) {
            radButTue.setChecked(true);
        }
        if (occurence.contains(Day.WEDNESDAY)) {
            radButWed.setChecked(true);
        }
        if (occurence.contains(Day.THURSDAY)) {
            radButThur.setChecked(true);
        }
        if (occurence.contains(Day.FRIDAY)) {
            radButFri.setChecked(true);
        }
        if (occurence.contains(Day.SATURDAY)) {
            radButSat.setChecked(true);
        }
        if (occurence.contains(Day.SUNDAY)) {
            radButSun.setChecked(true);
        }
    }
}


