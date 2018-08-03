package com.demo.santhi.cameragps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Jaladi Udaya Santhi on 11-07-2018.
 */

public class GPSLocation implements LocationListener {
    public Location location;
    protected LocationManager locationManager;
    Context appContext = null;

    public static  String getUtmText() {
        return utmText;
    }

    public static  Double getLat() {
        return lattitude;
    }

    public static  Double getLong() {
        return longitude;
    }

    public void setUtmText(String utmText) {
        this.utmText = utmText;
    }
    public void setLatitude(Double lattitude) {
        this.lattitude = lattitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public static String utmText;
    public static Double lattitude;
    public static Double longitude;

    public GPSLocation(Context context) {
        appContext = context;
        locationManager = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
    }

    public Location getLocation(String s) {
        if (locationManager.isProviderEnabled(s)) {
            if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast.makeText(appContext, "PERMISSION NOT GRANTED", Toast.LENGTH_SHORT).show();
                return null;

            }
            locationManager.requestLocationUpdates(s, 0, 0, this);
        }
        if(null != location){
            location = locationManager.getLastKnownLocation(s);
            return location;
        }
        return null;
    }
    @Override
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        UTMConverter mUTM = new UTMConverter();
        String utmString = mUTM.latLon2UTM(latitude, longitude);

        View v = View.inflate(appContext, R.layout.activity_camera, null);

       /* utmText = (TextView) v.findViewById(R.id.textView);
        utmText.setText(utmString);*/
        String locationCombined = utmString + "\n\n " + "Latitude: "+ latitude + " Longitude: " + longitude;
        setUtmText(locationCombined);
        setLatitude(latitude);
        setLongitude(longitude);
//        Toast.makeText(appContext, utmString, Toast.LENGTH_SHORT).show();
//        Toast.makeText(appContext, "Location: "+ latitude + " " + longitude, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText( appContext,"Gps Enabled",Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText( appContext,"Gps Disabled",Toast.LENGTH_SHORT ).show();
    }
}
