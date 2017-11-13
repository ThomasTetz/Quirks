package cmput301f17t12.quirks;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
        ArrayList<Day> Day = new ArrayList<Day>();
        Day.add(cmput301f17t12.quirks.Enumerations.Day.MONDAY);
         */
        addQuirkActivity = mActivityRule.getActivity();
    }

    @Test
    public void addQuirkCancelButton(){
        Intents.init();
        onView(withId(R.id.cancel_button)).perform(click());

    }

}
