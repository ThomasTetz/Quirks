package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.R;

public class EditQuirkClickedActivity extends AppCompatActivity {

    private static final String TAG = "Inside EditQuirk";
    private String type;
    private String title;
    private String reason;
    private String goal;
    private ArrayList<Day> occurence;
    public RadioButton radButMon;
    public RadioButton radButTue;
    public RadioButton radButWed;
    public RadioButton radButThur;
    public RadioButton radButFri;
    public RadioButton radButSat;
    public RadioButton radButSun;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quirk_clicked);
        Intent incomingIntent = getIntent();
        radButMon = (RadioButton)findViewById(R.id.QuirkEditradioButtonMon);
        radButTue = (RadioButton)findViewById(R.id.QuirkEditradioButtonTue);
        radButWed = (RadioButton)findViewById(R.id.QuirkEditradioButtonWed);
        radButThur = (RadioButton)findViewById(R.id.QuirkEditradioButtonThur);
        radButFri = (RadioButton)findViewById(R.id.QuirkEditradioButtonFri);
        radButSat = (RadioButton)findViewById(R.id.QuirkEditradioButtonSat);
        radButSun = (RadioButton)findViewById(R.id.QuirkEditradioButtonSun);

        EditText TitleEdit = (EditText)findViewById(R.id.QuirkeditTextTitle);
        EditText TypeEdit = (EditText)findViewById(R.id.QuirkeditTextType);
        EditText ReasonEdit = (EditText)findViewById(R.id.QuirkeditTextReason);
        EditText GoalEdit = (EditText)findViewById(R.id.QuirkeditTextGoal);

        /*
        Quirk QuirkLoad = (Quirk)getIntent().getSerializableExtra("Edit Quirk");

        TitleEdit.setText(QuirkLoad.getTitle());
        TypeEdit.setText(QuirkLoad.getType());
        ReasonEdit.setText(QuirkLoad.getReason());
        GoalEdit.setText(QuirkLoad.getGoalValue());
        */


    }

    // TODO:
    // Save button clicked -> save changes made to quirk, return to previous activity
    public void saveButtonClicked(View v){
        Log.d(TAG, "saveButtonClicked: im in here now ");
        type = ((EditText)findViewById(R.id.QuirkeditTextType)).getText().toString();
        title = ((EditText)findViewById(R.id.QuirkeditTextTitle)).getText().toString();
        reason = ((EditText)findViewById(R.id.QuirkeditTextReason)).getText().toString();
        goal = ((EditText)findViewById(R.id.QuirkeditTextGoal)).getText().toString();
        ArrayList<Day> QuirkOccurence = new ArrayList<Day>();

        if((type.equals(""))||(title.equals(""))||(goal.equals(""))){

        }

        else{
           QuirkOccurence = occurenceItemSelected();
            Intent intent = new Intent();
            int QuirkGoal = Integer.parseInt(goal);
            Date DatetoTest = new Date();

            //this should not be new Quirk it should just be changing the old quirk and replaceing it
            //So we need to use the DB and replace it with this one

            Quirk QuirkCreated = new Quirk(title,type,DatetoTest,QuirkOccurence,QuirkGoal);
            finish();

        }

    }


    // TODO:
    // Cancel button clicked -> discard any changes made to the quirk, return to previous activity
    public void cancelButtonClicked(View v){
        finish();


    }

    // TODO:
    // Delete button clicked -> delete quirk, return to previous screen
    public void DeleteButtonClicked(View v){
    //Delete from DB
        finish();
    }

    // TODO:
    // Occurence item was selected, update the occurence ArrayList
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

}


