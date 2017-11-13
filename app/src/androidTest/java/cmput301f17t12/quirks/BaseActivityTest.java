package cmput301f17t12.quirks;

import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.app.ActionBar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import cmput301f17t12.quirks.Activities.BaseActivity;
import cmput301f17t12.quirks.Activities.LoginActivity;
import cmput301f17t12.quirks.Activities.MainActivity;
import cmput301f17t12.quirks.Activities.NewEventActivity;
import cmput301f17t12.quirks.Activities.QuirksActivity;
import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by charleshoang on 2017-11-13.
 */

public class BaseActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    private MainActivity mainActivity;

    @Before
    public void initUsername() {
        //Initialize Base Activity
        mainActivity = mActivityRule.getActivity();

    }

    //Test to home button will take to MainActivity
    @Test
    public void actionHomeClick() {

        Intents.init();

        //Click action Home
        onView(withId(R.id.action_home))
                .perform(click());

        // Check that we are in main activity
        intended(hasComponent(MainActivity.class.getName()));

        Intents.release();

    }



    //Test to action_newevent button will take to NewEventActivity
    @Test
    public void actionNewEventClick() {

        Intents.init();

        //Click action_newevent
        onView(withId(R.id.action_newevent))
                .perform(click());
        // Check that we are in main activity
//        intended(hasComponent(NewEventActivity.class.getName()));

        Intents.release();

    }

}
