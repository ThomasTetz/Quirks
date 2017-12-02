package cmput301f17t12.quirks.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import cmput301f17t12.quirks.R;

import static cmput301f17t12.quirks.R.id.map;

public class MapActivity extends BaseActivity
        implements OnMapReadyCallback, OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;

    private TextView mTapTextView;
    private GoogleMap mMap;
    protected BottomNavigationView navigationView;

    private FusedLocationProviderClient userLocationClient;
    private Location userLoc;


    // [MAP]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTapTextView = (TextView) findViewById(R.id.tap_text);
        userLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Default Location for the User
        userLoc = new Location("");
        userLoc.setLatitude(53.5232);
        userLoc.setLongitude(-113.5263);

        Log.d("DEBUG", "Stage 1");

        //TODO: Maybe assign a flag based on the permission and then either show the users location or not

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.d("DEBUG", "Requesting Permission");
            // ActivityCompat#requestPermissions here to request the missing permissions, and then overriding
            // public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
            // to handle the case where the user grants the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
        else{
            Log.d("DEBUG", "Not Requesting Permission");

            userLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            Log.d("DEBUG", "Got location");
                            userLoc = location;

                            if (location != null) {

                                Log.d("DEBUG", "Default Location");
                                // Default Position -> University of Alberta
                                userLoc.setLatitude(53.5232);
                                userLoc.setLongitude(-113.5263);
                            }
                        }

                    });

        }

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        Button NearbyMapButton = (Button) findViewById(R.id.nearby_map_button);
        NearbyMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button FollowingMapButton = (Button) findViewById(R.id.following_map_button);
        FollowingMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button MyEventsMapButton = (Button) findViewById(R.id.my_events_map_button);
        MyEventsMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    // [MAP]
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        Log.d("DEBUG", "Map is ready");

        mMap = googleMap;

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);

        //Ualberta location - for testing
        //userLoc.setLatitude(53.5232);
        //userLoc.setLongitude(-113.5263);

        //TODO: Default location is based on Users Location -> Is default location calculated or just hardcoded??

        LatLng userLatLon = new LatLng(userLoc.getLatitude(), userLoc.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(userLatLon)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLon));
    }

    /**
     * This is called during the User Permission Request. This function checks if the user gave the
     * app permission and gets the user's location.
     * If the services utility returns null (or no permission is given). The user's location is
     * assigned to University of Alberta as the default TODO: CHANGE THIS
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("DEBUG", "Getting the user's location");

                    // I have surpressed this error
                    // - Currently tells me that I haven't checked for permission when I clearly have
                    //noinspection MissingPermission
                    userLocationClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {

                                    userLoc = location;

                                    if (location != null) {

                                        Log.d("DEBUG", "Default Location");
                                        // Default Position -> University of Alberta
                                        userLoc.setLatitude(53.5232);
                                        userLoc.setLongitude(-113.5263);
                                    }
                                }
                            });

                } else {
                    userLoc.setLatitude(53.5232);
                    userLoc.setLongitude(-113.5263);
                    // permission denied, boo!
                    // TODO: Handle this case
                }
                return;
            }

            // we can add other permission codes here if need-be
        }
    }

    @Override
    public void onCameraIdle() { mTapTextView.setText(mMap.getCameraPosition().toString());}

    @Override
    public void onMapClick(LatLng point) {
        mTapTextView.setText("tapped, point=" + point);
    }

    @Override
    public void onMapLongClick(LatLng point) { mTapTextView.setText("long pressed, point=" + point);}

    // [Navigation Bar]
    int getContentViewId() {
        return R.layout.activity_map;
    }

    // [Navigation Bar]
    int getNavigationMenuItemId() {
        return R.id.action_geomap;
    }

}