package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;
import cmput301f17t12.quirks.R;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback, BottomNavigationView.OnNavigationItemSelectedListener {

    /* Currently I have been able to get either:
        JUST THE MAP FRAGMENT WORKING (within the framelayout)
        OR JUST THE NAVIGATION BAR
        -- They don't like to work together
     */

    protected BottomNavigationView navigationView;

    // [MAP]
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(navigationView);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /* Non Map - Core basic testing
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }*/


    // [MAP]
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
        switch (item.getItemId()) {
            case R.id.action_geomap:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.action_social:
                // TODO: Part 5
//                startActivity(new Intent(this, SocialActivity.class));
//                break;
            case R.id.action_newevent:
                startActivity(new Intent(this, NewEventActivity.class));
                break;
            case R.id.action_quirklist:
                startActivity(new Intent(this, QuirksActivity.class));
                break;
            case R.id.action_home:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        finish();
        return true;
    }

    // [Navigation Bar]
    int getContentViewId() {
        return R.layout.activity_map;
    }

    // [Navigation Bar]
    int getNavigationMenuItemId() {
        return R.id.action_geomap;
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

}
