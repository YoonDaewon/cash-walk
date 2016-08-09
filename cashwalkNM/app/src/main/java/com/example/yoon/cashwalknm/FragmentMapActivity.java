package com.example.yoon.cashwalknm;

/**
 * Created by ydwin on 2016-08-09.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nhn.android.maps.NMapView;

public class FragmentMapActivity extends FragmentActivity {

    private NMapView mMapView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.framents);

        mMapView = (NMapView)findViewById(R.id.mapView);

        // initialize map view
        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();
    }
}