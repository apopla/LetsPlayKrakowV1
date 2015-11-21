package com.example.kitowcy.letsplaykrakow;

import android.content.res.Configuration;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    public DrawerLayout getDrawer() {
        return drawer;
    }

    public static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;                              // Declaring the Toolbar Object
    private DrawerLayout drawer;                                  // Declaring DrawerLayout
    private ActionBarDrawerToggle mDrawerToggle;
    public static int currentFragmentDisplayedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_material);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onPostCreate()");
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }
    private void setupDrawer() {
        Log.d(TAG, "setupDrawer()");
        boolean userIsLogged = false;
        drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // drawer object Assigned to the view
        android.support.v7.widget.RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        recyclerView.setHasFixedSize(true);// Letting the system know that the list objects are of fixed size
        MaterialDrawerAdapter drawerAdapter = AdapterCreator.getAdapter(drawer, userIsLogged, this);
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
