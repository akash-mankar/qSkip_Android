package com.example.Qskip;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private static final int READ_LOCATION = 1;
    private Location curLoc = new Location((String)null);
    private boolean locObtained = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        geoLocator();
    }

    public void login(View view) {
        //nothing for now
        Intent intent = new Intent(this, StoresActivity.class);
        Double lat = curLoc.getLatitude();
        Double lon = curLoc.getLongitude();
        intent.putExtra("lat", lat.toString());
        intent.putExtra("lon", lon.toString());
        startActivity(intent);
    }

    public void geoLocator() {
        locObtained = false;
        Intent intent = new Intent(this, LocatorActivity.class);
        startActivityForResult(intent, READ_LOCATION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case READ_LOCATION:
                if (resultCode == RESULT_OK) {
                    String loc = data.getStringExtra("location");
                    String[] latlng = loc.split(" ");
                    double lat = Location.convert(latlng[0]);
                    double lng = Location.convert(latlng[1]);
                    curLoc.setLatitude(lat);
                    curLoc.setLongitude(lng);
                    locObtained = true;
                }
                else {
                    // curLoc = null;
                }
                break;
        }
    }
}

