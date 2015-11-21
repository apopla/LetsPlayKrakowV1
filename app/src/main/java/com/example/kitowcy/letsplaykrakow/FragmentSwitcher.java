package com.example.kitowcy.letsplaykrakow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kitowcy.letsplaykrakow.entities.MainActivity;

/**
 * Created by Lukasz Marczak on 2015-08-28.
 * Here we put all common methods to switch to another fragment from parent activity
 */

public class FragmentSwitcher {

    public static final String TAG = FragmentSwitcher.class.getSimpleName();

    /**
     * Switching between fragments
     * @param activity - activity that is connected with the fragment
     * @param fragmentUnit - fragment that you would like to switch
     * @param placeHolderResource - placeholder for fragments
     * @param bundle
     */
    public static void switchToFragment(AppCompatActivity activity, FragmentUnit fragmentUnit, int placeHolderResource, Bundle bundle) {
        Log.d(TAG, "Switching to fragment: " + fragmentUnit.getFragmentClass().getSimpleName());

        if (activity.getApplicationContext() != null) {
            try {

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                Fragment selectedFragment = fragmentUnit.getFragmentClass().newInstance();
                MainActivity.currentFragmentDisplayedId = fragmentUnit.getId();
                selectedFragment.setArguments(bundle);
                transaction.replace(placeHolderResource, selectedFragment);
                transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
                transaction.commit();

                if (activity.getSupportActionBar() != null)
                    activity.getSupportActionBar().setElevation(0);

                if (activity instanceof MainActivity) {
                    DrawerLayout drawer = ((MainActivity) activity).getDrawer();

                    if (drawer != null && drawer.isShown()) {
                        drawer.closeDrawer(GravityCompat.START);
                    }
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void switchToFragment(AppCompatActivity activity, Fragment fragment, int placeHolderResource, Bundle bundle) {
        Log.d(TAG, "switchToFragment() called with: " + "activity = [" + activity + "], fragment = [" + fragment + "], placeHolderResource = [" + placeHolderResource + "], bundle = [" + bundle + "]");

        if(activity.getApplicationContext() != null) {
            if (fragment != null) {
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .add(placeHolderResource, fragment, "TAG")
                        .commit();
            }
        }
    }

}
