package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;

import com.example.kitowcy.letsplaykrakow.entities.fragments.GoogleMapFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.PlacesFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.StartFragment;


public enum FragmentUnit {

    SPLASH(-1, GoogleMapFragment.class),
    MAP(0, GoogleMapFragment.class),
    ROUTES(1, StartFragment.class),
    ACHIEVEMENTS(2, StartFragment.class),
    PLACES(3, PlacesFragment.class);

    /**
     * Subclass of base Fragment class
     */
    private Class<? extends Fragment> fragmentClass;

    /**
     * Id related to fragment
     */
    private int id;

    FragmentUnit(int id, Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }
}
