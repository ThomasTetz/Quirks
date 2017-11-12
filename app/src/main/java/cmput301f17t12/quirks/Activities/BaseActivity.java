package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;
import cmput301f17t12.quirks.R;

// Source: https://blog.fossasia.org/using-activities-with-bottom-navigation-view-in-phimpme-android/
public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "BaseActivity");
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(navigationView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("testing", "am here");
        switch (item.getItemId()) {
            case R.id.action_geomap:
                // TODO: Part 5
//                startActivity(new Intent(this, GeomapActivity.class));
//                break;
            case R.id.action_social:
                // TODO: Part 5
//                startActivity(new Intent(this, SocialActivity.class));
                startActivity(new Intent(this, FakeLoginActivity.class));
                break;
            case R.id.action_newevent:
                // TODO: Part 4
//                startActivity(new Intent(this, NewEventActivity.class));
//                break;
            case R.id.action_quirklist:
                startActivity(new Intent(this, QuirksActivity.class));
                break;
            case R.id.action_home:
                Log.d("testing", "LOLOOLLO");
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        finish();
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    abstract int getContentViewId();

    abstract int getNavigationMenuItemId();
}
