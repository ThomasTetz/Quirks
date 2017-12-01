package cmput301f17t12.quirks.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

/**
 * Created by root on 11/30/17.
 */

public class FindFriendActivity extends SocialActivity {
    public User currentlylogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("dbSettings", Context.MODE_PRIVATE);
        String jestID = settings.getString("jestID", "defaultvalue");

        if (jestID.equals("defaultvalue")) {
            Log.i("Error", "Did not find correct jestID");
        }



    }


    @Override
    int getContentViewId() {
        return R.layout.activity_findfriends;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_findFriends;
    }
}
