package cmput301f17t12.quirks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Activities.AddQuirkActivity;
import cmput301f17t12.quirks.Activities.QuirksActivity;
import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Models.Quirk;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by root on 11/13/17.
 */

@RunWith(AndroidJUnit4.class)
public class AddQuirkActivityTest {
    Quirk Quirktest;
    String quirky;
    @Rule
    public ActivityTestRule<AddQuirkActivity> mActivityRule = new ActivityTestRule<>(AddQuirkActivity.class);

    private AddQuirkActivity addQuirkActivity;

    @Before
    public void initQuirk(){
      // quirky = "testing123";
        /* Date testdate = new Date();
        ArrayList<Day> Day = new ArrayList<Day>();k
        Day.add(cmput301f17t12.quirks.Enumerations.Day.MONDAY);
         */
        addQuirkActivity = mActivityRule.getActivity();
    }

    @Test
    public void addQuirkTitleTest(){
        Intents.init();
        String title = "Title";
        onView(withId(R.id.QuirkeditTextTitle))
                .perform(typeText("Title"), closeSoftKeyboard());
        onView(withId(R.id.QuirkeditTextTitle))
                .check(matches(withText(title)));
        Intents.release();
    }

    @Test
    public void addQuirkTypeTest(){
        Intents.init();
        String type = "Type";
        onView(withId(R.id.QuirkeditTextType)).perform(typeText("Type"),closeSoftKeyboard());
        onView(withId(R.id.QuirkeditTextType)).check(matches(withText(type)));
        Intents.release();
    }

    @Test
    public void addQuirkReasonTest(){
        Intents.init();
        String reason = "Reason";
        onView(withId(R.id.QuirkeditTextReason)).perform(typeText("Reason"),closeSoftKeyboard());
        onView(withId(R.id.QuirkeditTextReason)).check(matches(withText(reason)));
        Intents.release();

    }

    @Test
    public void addQuirkGoalTest(){
        Intents.init();
        String goal = "15";
        onView(withId(R.id.QuirkeditTextGoal)).perform(typeText(String.valueOf("15")));
        onView(withId(R.id.QuirkeditTextGoal)).check(matches(withText(goal)));
        Intents.release();

    }

   /* @Test
    public void addQuirkDateTest(){
        Intents.init();
        String date = "11/3/2017";
        DatePickerDialog.OnDateSetListener SelectDateListener;
        SelectDateListener = new DatePickerDialog.OnDateSetListener().;
        onView(withId(R.id.textViewSelDate)).perform(DatePickerDialog.OnDateSetListener().);
        onView(withId(R.id.textViewSelDate)).check(matches(withText(date)));
        Intents.release();
    }
    */

    @Test
    public void addQuirkCancelButton(){
        Intents.init();
        onView(withId(R.id.CancelBut)).perform(click());
        Intents.release();
    }

}
