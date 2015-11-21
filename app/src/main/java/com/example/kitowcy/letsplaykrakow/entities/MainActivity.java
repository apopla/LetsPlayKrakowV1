package com.example.kitowcy.letsplaykrakow.entities;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.kitowcy.letsplaykrakow.AdapterCreator;
import com.example.kitowcy.letsplaykrakow.FragmentSwitcher;
import com.example.kitowcy.letsplaykrakow.FragmentUnit;
import com.example.kitowcy.letsplaykrakow.MaterialDrawerAdapter;
import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.data.PlaceCreator;
import com.example.kitowcy.letsplaykrakow.beacon.KontaktBeaconService;
import com.example.kitowcy.letsplaykrakow.location.LocationRequestBuilder;
import com.example.kitowcy.letsplaykrakow.location.LocationService;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    private ServiceConnection serviceConnection;

    private Messenger serviceMessenger;
    public static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private DrawerLayout drawer;                                  // Declaring DrawerLayout
    private ActionBarDrawerToggle mDrawerToggle;
    public static int currentFragmentDisplayedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        PlaceCreator.load(this);

        setContentView(R.layout.activity_main_material);
        setupDrawer();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showSplashFragment();
        setupLocationService();
        startBeaconService();
    }

    private void setupLocationService() {
        Bundle bundle = new Bundle();
        LocationRequestBuilder locationRequestBuilder = new LocationRequestBuilder()
                .withAccuracy(LocationRequestBuilder.BALANCED)
                .withUpdateInterval(400).withFastestInterval(200);

        bundle.putSerializable("LOCATION_REQUEST_BUILDER", locationRequestBuilder);
        startServiceWithBundle(bundle);
    }

    public void startServiceWithBundle(Bundle bundle) {
        Log.d(TAG, "startServiceWithBundle ");
        Intent intent = new Intent(getApplicationContext(), LocationService.class);
        intent.putExtras(bundle);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate()");
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy ");
        LocationService.stop();
    }

    private void showSplashFragment(){
        FragmentSwitcher.switchToFragment(this, FragmentUnit.SPLASH, R.id.activity_main_fragment_placeholder, null);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMapFragment();
            }
        }, 2000);

    }

    private void showMapFragment(){

        FragmentSwitcher.switchToFragment(this, FragmentUnit.MAP, R.id.activity_main_fragment_placeholder, null);

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        boolean isRunning = false;
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                isRunning = true;
                break;
            }
        }
        Log.d(TAG, "isMyServiceRunning " + isRunning);
        return isRunning;
    }

    private void startBeaconService() {
        Log.d(TAG, "startBeaconService() called with: " + "");

        Intent intent = new Intent(this, KontaktBeaconService.class);
        startService(intent);
    }

    private void setupDrawer() {
        Log.d(TAG, "setupDrawer()");

        drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // drawer object Assigned to the view
        android.support.v7.widget.RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        recyclerView.setHasFixedSize(true);// Letting the system know that the list objects are of fixed size
        MaterialDrawerAdapter drawerAdapter = AdapterCreator.getAdapter(drawer, this);
        recyclerView.setAdapter(drawerAdapter);                              // Setting the adapter to RecyclerView

        //update sth on start

        recyclerView.setLayoutManager(new LinearLayoutManager(this));                 // Setting the layout Manager
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        }; // drawer Toggle Object Made
        drawer.setDrawerListener(mDrawerToggle); // drawer Listener set to the drawer toggle
        mDrawerToggle.syncState();

        ActionBar actionBar = getActionBar();
        if (actionBar==null){
            Log.d(TAG, "action bar is null :(");
        } else {
            Log.d(TAG, "action bar is not null!!! :)");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");

        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
