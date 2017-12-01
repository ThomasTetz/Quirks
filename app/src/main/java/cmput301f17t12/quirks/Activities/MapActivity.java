package cmput301f17t12.quirks.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cmput301f17t12.quirks.Helpers.BottomNavigationViewHelper;
import cmput301f17t12.quirks.R;

import static cmput301f17t12.quirks.R.id.map;

public class MapActivity extends BaseActivity
        implements OnMapReadyCallback, OnMapClickListener, OnMapLongClickListener, OnCameraIdleListener {

    private TextView mTapTextView;
    private GoogleMap mMap;
    protected BottomNavigationView navigationView;

    // [MAP]
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mTapTextView = (TextView) findViewById(R.id.tap_text);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

    }

    // [MAP]
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        mMap = googleMap;

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnCameraIdleListener(this);

        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    // [Navigation Bar]
    int getContentViewId() {
        return R.layout.activity_map;
    }

    // [Navigation Bar]
    int getNavigationMenuItemId() {
        return R.id.action_geomap;
    }


    @Override
    public void onCameraIdle() { mTapTextView.setText(mMap.getCameraPosition().toString());}

    @Override
    public void onMapClick(LatLng point) {
        mTapTextView.setText("tapped, point=" + point);
    }


    @Override
    public void onMapLongClick(LatLng point) { mTapTextView.setText("long pressed, point=" + point);}
}