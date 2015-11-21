package com.example.kitowcy.letsplaykrakow;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lukasz Marczak on 2015-11-18.
 */
public class AdapterCreator {

    private static final String TAG = AdapterCreator.class.getSimpleName();

    private AdapterCreator() {
    }

    private static List<String> getDataSet(boolean isUserLogged) {
        List<String> titles = new ArrayList<>();
        titles.add("Journeys");
        titles.add("Offers");
        titles.add("Timetable");
        titles.add("Information");
        titles.add("Settings");
        if (isUserLogged) {
            //logged user
            titles.clear();
            titles.add("Journeys");
            titles.add("Offers");
            titles.add("Timetable");
            titles.add("Information");
            titles.add("Notifications");
            titles.add("Settings");
        }
        return titles;
    }

    public static MaterialDrawerAdapter getAdapter(final DrawerLayout drawer, final boolean isLoggedIn, final AppCompatActivity context) {

//        String customerName = CustomerUtils.getCustomerFirstName(context) + " " + CustomerUtils.getCustomerLastName(context);
//        String customerEmail = CustomerUtils.getCustomerEmail(context);
        String customerPhotoUrl = "";



        MaterialDrawerAdapter
                mAdapter = new MaterialDrawerAdapter(context, getDataSet(isLoggedIn), "",
                "", customerPhotoUrl, isLoggedIn);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        mAdapter.setOnClickListener(new MaterialDrawerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                /**
                 * switch to selected fragment
                 */
                switch (position) {
                    case 0:
                        FragmentSwitcher.switchToFragment(context, FragmentUnit.START, R.id.activity_main_fragment_placeholder, null);
                        break;
                    default:
                    case 1:
                        FragmentSwitcher.switchToFragment(context, FragmentUnit.SETTINGS, R.id.activity_main_fragment_placeholder, null);
                        break;
                }
                if (drawer != null)
                    drawer.closeDrawers();
            }
        });
        return mAdapter;
    }

}
