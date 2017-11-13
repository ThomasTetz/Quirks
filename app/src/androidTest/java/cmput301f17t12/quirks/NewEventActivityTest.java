package cmput301f17t12.quirks;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.VerificationMode;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import cmput301f17t12.quirks.Activities.LoginActivity;
import cmput301f17t12.quirks.Activities.MainActivity;
import cmput301f17t12.quirks.Activities.NewEventActivity;
import cmput301f17t12.quirks.Activities.QuirksActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.Intents.times;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

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
    public void initUsername() {
        // Specify a valid comment
        comment = "testing comment";
        loginActivity = mActivityRule.getActivity();
    }

    @Test
    public void addImageButton(){
        //Need to initialize intents or you will get null exception
        Intents.init();

        //Login with user testing 123 and go to NewEvent ACtivity to have
        //quirks to log
        onView(withId(R.id.loginUser))
                .perform(typeText("testing123"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn))
                .perform(click());
        onView(withId(R.id.action_newevent))
                .perform(click());

        //Click Browse Button
        onView(withId(R.id.browse_button))
                .perform(click());
        // Check that the intent is changed to main activity
        intended(allOf(hasAction(equalTo(Intent.ACTION_GET_CONTENT)),
                hasType(is("image/*"))));
        Intents.release();

    }

    @Test
    public void saveButton(){
        //Will need to go createa quirk then save
        Intents.init();


        //Login with user testing 123 and go to NewEvent ACtivity to have
        //quirks to log
        onView(withId(R.id.loginUser))
                .perform(typeText("testing123"), closeSoftKeyboard());
        onView(withId(R.id.loginBtn))
                .perform(click());
        onView(withId(R.id.action_newevent))
                .perform(click());

        //insert comment and save event
        onView(withId(R.id.comment_edittext))
                .perform(typeText(comment), closeSoftKeyboard());
        onView(withId(R.id.save_button))
                .perform(click());
        intended(hasComponent(MainActivity.class.getName()), times(2));
        Intents.release();
    }

    @Test
    public void cancelButton(){
        //Will need to go createa quirk then save
        Intents.init();


        //Login with user testing 123 and go to NewEvent ACtivity to have
        //quirks to log
        onView(withId(R.id.loginUser))
                .perform(typeText("testing123"), closeSoftKeyboard());
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


}
