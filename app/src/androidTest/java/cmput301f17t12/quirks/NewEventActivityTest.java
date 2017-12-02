package cmput301f17t12.quirks;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cmput301f17t12.quirks.Activities.LoginActivity;
import cmput301f17t12.quirks.Activities.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by charleshoang on 2017-11-13.
 */

@RunWith(AndroidJUnit4.class)
public class NewEventActivityTest {

    String comment;
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);
    private LoginActivity loginActivity;

    @Before
    public void initialize() {
        // Specify a valid comment
        comment = "testing comment";
        loginActivity = mActivityRule.getActivity();
    }

    @Test
    public void cancelButton(){
        //Will need to go createa quirk then save
        Intents.init();


        //Login with user testing 123 and go to NewEvent ACtivity to have
        //quirks to log
        onView(withId(R.id.loginUser))
                .perform(typeText("intenttesting"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn))
                .perform(click());
        onView(withId(R.id.action_newevent))
                .perform(click());

        //Cancel button in add Events
        onView(withId(R.id.cancel_button))
                .perform(click());
        intended(hasComponent(MainActivity.class.getName()), times(2));
        Intents.release();
    }

    @Test
    public void saveButton(){
        //Will need to go createa quirk then save
        Intents.init();


        //Login with user testing 123 and go to NewEvent ACtivity to have
        //quirks to log
        onView(withId(R.id.loginUser))
                .perform(typeText("intenttesting"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn))
                .perform(click());
        onView(withId(R.id.action_newevent))
                .perform(click());

        //insert comment and save event
        onView(withId(R.id.comment_edittext))
                .perform(typeText(comment), closeSoftKeyboard());
        onView(withId(R.id.save_button))
                .perform(click());
        onView(withId(R.id.action_quirklist))
                .perform(click());

        onData(anything()).inAdapterView(withId(R.id.quirk_listview)).atPosition(0).
                onChildView(withId(R.id.quirk_button)).perform(click());

        //click on view/edit/delete button for latest event
        final int[] numberOfAdapterItems = new int[1];
        onView(withId(R.id.el_eventslistview)).check(matches(new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                ListView listView = (ListView) view;

                //here we assume the adapter has been fully loaded already
                numberOfAdapterItems[0] = listView.getAdapter().getCount();

                return true;
            }
            @Override
            public void describeTo(Description description) {

            }
        }));
        onData(anything()).inAdapterView(withId(R.id.el_eventslistview)).atPosition(numberOfAdapterItems[0] - 1).
                onChildView(withId(R.id.el_eventview)).perform(click());
        onView(withId(R.id.comment_edittext)).check(matches(withText(comment)));
        Intents.release();
    }


    //TODO: Intent to test image
//    @Test
//    public void addImageButton(){
//        //Need to initialize intents or you will get null exception
//        Intents.init();
//
//        //Login with user testing 123 and go to NewEvent ACtivity to have
//        //quirks to log
//        onView(withId(R.id.loginUser))
//                .perform(typeText("intenttesting"), closeSoftKeyboard());
//        onView(withId(R.id.loginBtn))
//                .perform(click());
//        onView(withId(R.id.action_newevent))
//                .perform(click());
//
//        //Click Browse Button
//        onView(withId(R.id.browse_button))
//                .perform(click());
//        // Check that the intent is changed to main activity
//        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
//        Intents.release();
//
//    }




}
