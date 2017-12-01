package cmput301f17t12.quirks.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cmput301f17t12.quirks.R;

public class TradeActivity extends SocialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_trade;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.action_trade;
    }
}
