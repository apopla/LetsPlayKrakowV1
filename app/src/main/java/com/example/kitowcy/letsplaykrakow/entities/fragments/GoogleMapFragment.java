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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.kitowcy.letsplaykrakow.Constants;
import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.FilterDialogBuilder;
import com.example.kitowcy.letsplaykrakow.adapters.FilterBuilder;
import com.example.kitowcy.letsplaykrakow.data.Place;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;
import com.example.kitowcy.letsplaykrakow.location.LocationData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class GoogleMapFragment extends Fragment {

    private static final String TAG = GoogleMapFragment.class.getSimpleName();
    private GoogleMap map;

    private boolean locationFound = false;
    private RelativeLayout mapLayout;
    private FilterBuilder currentFilter;
    public static GoogleMapFragment instance;

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
        MainActivity.currentFragmentDisplayedId = Constants.MAP;
        instance = this;
        currentFilter = new FilterBuilder().withAll();
        getActivity().setTitle("Krakow Places Map");
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

    public void setupFilterDialog() {
        FilterDialogBuilder.build(getActivity(), currentFilter, new FilterDialogBuilder.OnRefreshListener() {
            @Override
            public void onRefresh(FilterBuilder currentFilter) {
                redrawMarkers();
            }
        });
    }

    private void redrawMarkers() {
        Log.d(TAG, "redrawMarkers ");
        drawMarkers(map);
        updateMarker(LocationData.getCurrentPosition());
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
            if (action.equals(Constants.LOCATION_UPDATE_BROADCAST)) {
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
        Log.d(TAG, "showProgressBar " + show);
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
                drawMarkers(map);
                updateMarker(LocationData.getCurrentPosition());
            }
        });
        showProgressBar(false);
    }

    private void drawMarkers(GoogleMap map) {
        Realm realm = Realm.getInstance(getActivity());
        RealmResults<Place> places = realm.where(Place.class).findAll();
        if (places != null) {
            map.clear();
            List<Place> dataSet = new ArrayList<>();
            for (Place place : places) {
                if (place.getCategory().equals("CULTURE") && currentFilter.contains(FilterBuilder.CULTURE))
                    dataSet.add(place);
                if (place.getCategory().equals("FOOD") && currentFilter.contains(FilterBuilder.FOOD))
                    dataSet.add(place);
                if (place.getCategory().equals("MONUMENTS") && currentFilter.contains(FilterBuilder.MONUMENTS))
                    dataSet.add(place);
                if (place.getCategory().equals("ENTERTAINMENT") && currentFilter.contains(FilterBuilder.ENTERTAINMENT))
                    dataSet.add(place);
            }

            for (Place place : dataSet) {
                Log.d(TAG, "draw new Marker: " + place.getName());
//                Marker m =
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(place.getLatitude(), place.getLongitude()))
                        .title(place.getName())
                        .snippet(place.getAddress())
                        .icon(BitmapDescriptorFactory.fromResource(getCategorizedResource(place.getCategory()))));
            }
        }
    }

    private int getCategorizedResource(String category) {

        if (category.equals("FOOD"))
            return R.drawable.marker_food;
        else if (category.equals("ENTERTAINMENT"))
            return R.drawable.marker_fun;
        else if (category.equals("CULTURE"))
            return R.drawable.marker_culture;
        else if (category.equals("CULTURE"))
            return R.drawable.marker_culture;
        else
            return R.drawable.marker_monuments;
    }

    @Override
    public void onResume() {
        setupMap();
        super.onResume();
        Log.d(TAG, "onResume ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.filter) {
            setupFilterDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        instance = this;
    }

    @Override
    public void onStop() {
        instance = null;
        Log.d(TAG, "onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        instance = null;
        super.onDetach();
        Log.d(TAG, "onDetach ");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(locationUpdateReceiver);
    }
}
