package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cmput301f17t12.quirks.R;

/**
 * Created by root on 11/29/17.
 */

public class SocialActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    int getContentViewId() {
        return R.layout.activity_social;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_social;
    }
}
