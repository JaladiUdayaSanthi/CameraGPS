package com.demo.santhi.cameragps;

import android.content.Intent;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Jaladi Udaya Santhi on 11-07-2018.
 */

public class ViewMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Double lattitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        lattitude = intent.getDoubleExtra("LATTITUDE", 0);
        longitude = intent.getDoubleExtra("LONGITUDE", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // mMap.getUiSettings().setZoomControlsEnabled(true);
        // mMap.setMinZoomPreference(11);
        setLocationsOnMap(lattitude, longitude);
    }

    private void setLocationsOnMap(Double lattitude, Double longitude) {
        LatLng latLong = new LatLng(lattitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        markerOptions.position(latLong).icon(icon);

        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 14f));
    }


}
