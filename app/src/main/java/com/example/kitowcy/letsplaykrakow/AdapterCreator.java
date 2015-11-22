package com.example.kitowcy.letsplaykrakow;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdapterCreator {
    private static final String TAG = AdapterCreator.class.getSimpleName();

    private AdapterCreator() {
    }

    private static List<String> getDataSet() {
        List<String> titles = new ArrayList<>();
        titles.add("Map");
        titles.add("Routes");
        titles.add("Places");
        titles.add("Achievements");

        return titles;
    }

    public static MaterialDrawerAdapter getAdapter(final DrawerLayout drawer, final AppCompatActivity context) {

        MaterialDrawerAdapter
                mAdapter = new MaterialDrawerAdapter(context, getDataSet());       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mAdapter.setOnClickListener(new MaterialDrawerAdapter.onItemClickListener() {
            @Override
            public void onClick(int position) {
                Log.d(TAG, "clicked: " + position);
                switch (position) {
                    case -1:
                        //// TODO: 21.11.15 splash screen
                        switchTo(context, FragmentUnit.SPLASH);
                        break;
                    case 0:
                        switchTo(context, FragmentUnit.MAP);
                        break;
                    case 1:
                        switchTo(context, FragmentUnit.ROUTES);
                        break;
                    case 2:
                        switchTo(context, FragmentUnit.PLACES);
                        break;
                    case 3:
                        switchTo(context, FragmentUnit.RECYCLER);
                        break;
                }
                if (drawer != null)
                    drawer.closeDrawers();
            }
        });
        return mAdapter;
    }

    private static void switchTo(AppCompatActivity context, FragmentUnit fragmentUnit) {
        FragmentSwitcher.switchToFragment(context, fragmentUnit, R.id.activity_main_fragment_placeholder, null);
    }

}
