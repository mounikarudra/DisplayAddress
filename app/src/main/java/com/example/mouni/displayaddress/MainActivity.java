package com.example.mouni.displayaddress;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {

   final String LOG_TAG = "Location Human Readable" ;
    LocationListener _gpsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        _gpsListener = new MyLocationListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, _gpsListener);


       Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();

        if (location == null){
            Log.d(LOG_TAG, "No 'Last Known Location' available");
            return;
        }

        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList =geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

           // int addressesReturned = addressList.size();
            //Log.d(LOG_TAG, String.format("Number of addresses returned: %d", addressesReturned));

            for(Address address:addressList) {
                displayAddressLines( address);
            textView2.setText(""+location.getLatitude());
                textView3.setText(""+location.getLongitude());
                textView4.setText(""+macAddress);
               // AddressHelper.logAddress(LOG_TAG, address);
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
    private void displayAddressLines(Address address) {
        int lastIndex = address.getMaxAddressLineIndex();
        for(int index = 0; index <= lastIndex; index++) {
            String addressLine = address.getAddressLine(index);
            addLineToDisplay(addressLine);
        }
       addLineToDisplay("");

    }

    private void addLineToDisplay(CharSequence displayLine) {
        TextView textView = (TextView) findViewById(R.id.textView1);

        CharSequence existingText = textView.getText();
       CharSequence newText = existingText + " " + displayLine;

        textView.setText(newText);
    }


}
