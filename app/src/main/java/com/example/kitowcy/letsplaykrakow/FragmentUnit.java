package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;

import com.example.kitowcy.letsplaykrakow.entities.fragments.GoogleMapFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.StartFragment;


public enum FragmentUnit {

    SPLASH(-1, StartFragment.class),
    MAP(0, GoogleMapFragment.class),
    ROUTES(1, GoogleMapFragment.class),
    ACHIEVEMENTS(2, GoogleMapFragment.class),
    PLACES(3, GoogleMapFragment.class);

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
