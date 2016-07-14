package com.example.mouni.displayaddress;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by MOUNI on 10-Jul-16.
 */
public class MyLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        String provider = location.getProvider();
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        //float accuracy = location.getAccuracy();
        //long time = location.getTime();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
