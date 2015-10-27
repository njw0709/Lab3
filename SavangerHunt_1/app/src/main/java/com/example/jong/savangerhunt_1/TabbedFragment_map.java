package com.example.jong.savangerhunt_1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by root on 10/22/15.
 */
public class TabbedFragment_map extends android.support.v4.app.Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks{
    public static final String ARG_SECTION_NUMBER = "2";
    private GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Boolean mapReady = false;
    private boolean hasarrived = false;
    private int mProgressStatus = 0;
    private ProgressBar mProgress;
    private Handler mHandler = new Handler();
    private GPSTracker gps;
    public TabbedFragment_map() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.maplayout, container, false);
        create_button(v,"camera");
        create_progressbar(v);

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return v;
    }


    public void create_button(View v, String button) {
        switch (button) {
            //TODO:button name change to small case
            case ("camera"): {
                Button Camera = (Button) v.findViewById(R.id.Camera_button);
                Camera.setEnabled(false);
            }
        }

    }


    public void create_progressbar(View v) {
        mProgress = (ProgressBar) v.findViewById(R.id.progressbar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressStatus < 100) {
                    mProgressStatus = doWork();

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgress.setProgress(mProgressStatus);
                        }
                    });
                }
            }
        });
    }

    public int doWork() {
        int proximity = 0;
        while (proximity <= 1000000) {
            proximity++;
            return (int) proximity;
        }
        return 100;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("ONCONNECTED","this was called");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        mapReady = true;
        googleMap = mMap;
        updateMapWithLocation();
    }
    private void updateMapWithLocation() {
        if (mapReady) {
            LatLng locLL = new LatLng(42.29,-71.26);
            googleMap.addMarker(new MarkerOptions()
                    .position(locLL)
                    .title("Your current location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(locLL));
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        }
    }
}
