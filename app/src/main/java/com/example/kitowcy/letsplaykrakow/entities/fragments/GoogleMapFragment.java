package com.example.kitowcy.letsplaykrakow.entities.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.kitowcy.letsplaykrakow.Constants;
import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.location.LocationData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapFragment extends Fragment {

    private static final String TAG = GoogleMapFragment.class.getSimpleName();
    private GoogleMap map;

    private boolean locationFound = false;
    private RelativeLayout mapLayout;

    public static GoogleMapFragment newInstance() {
        GoogleMapFragment fragment = new GoogleMapFragment();
        return fragment;
    }

    public GoogleMapFragment() {
        // Required empty public constructor
        Log.d(TAG, "constructor of GoogleMapFragment ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");
        getActivity().setTitle("Current Location");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView ");
        View view = inflater.inflate(R.layout.fragment_google_map, container, false);
        getViews(view);
        return view;
    }

    private void getViews(View view) {
        mapLayout = (RelativeLayout) view.findViewById(R.id.map_layout);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach ");
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(locationUpdateReceiver);
    }

    private BroadcastReceiver locationUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.v(TAG, "onReceive(): received action: " + action);

            /**
             Receiving location update broadcasts - LocationService
             */
            if (action.equals(Constants .LOCATION_UPDATE_BROADCAST)) {
                // Get extra data included in the Intent
                Location location = intent.getParcelableExtra(Constants.LOCATION_UPDATE_KEY);
                Log.d("locationUpdateReceiver", "Got new location! ");

                if (location != null) {
                    try {
                        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                        updateMarker(position);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private void updateMarker(LatLng position) {
        Log.d(TAG, "updateMarker()");
        if (!LocationData.locationChanged()) {
            Log.d(TAG, "location not changed");
            return;
        }
        showProgressBar(true);

        //show default current position marker
//        showCurrentMarkerStationary(position);
//        showStopsNearbyRadius(position);
//        updateMarkersOnResume(map);

        //move camera (if location found for the first time)
        if (!locationFound) {
            map.clear();
            CameraUpdate update = CameraUpdateFactory.newLatLng(position);
            map.moveCamera(update);
//            CameraUpdate zoom = CameraUpdateFactory.zoomTo(5);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 17));
//            map.animateCamera(zoom);
            map.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(LocationData.getCurrentPosition())
                    .title("My Location"));
            locationFound = true;
        }
        showProgressBar(false);
    }

    private void showProgressBar(boolean show) {
    }

    public void setupMap() {
        Log.d(TAG, "setupMap()");

        showProgressBar(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.v(TAG, "setupMap() : onMapReady()");

                LocalBroadcastManager.getInstance(getActivity()).registerReceiver(locationUpdateReceiver,
                        new IntentFilter(Constants.LOCATION_UPDATE_BROADCAST));
                updateMarker(LocationData.getCurrentPosition());
            }
        });
        showProgressBar(false);
    }

    @Override
    public void onResume() {
        setupMap();
        super.onResume();
        Log.d(TAG, "onResume ");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach ");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(locationUpdateReceiver);
    }
}