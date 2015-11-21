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
           }
       });
        return mAdapter;
    }

}
