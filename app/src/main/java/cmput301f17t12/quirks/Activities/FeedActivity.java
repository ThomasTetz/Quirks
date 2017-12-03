package cmput301f17t12.quirks.Activities;

import android.os.Bundle;

import cmput301f17t12.quirks.R;

public class FeedActivity extends SocialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_feed;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_feed;
    }
}
