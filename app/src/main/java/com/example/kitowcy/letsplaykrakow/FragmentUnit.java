package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;

import com.example.kitowcy.letsplaykrakow.entities.fragments.GoogleMapFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.StartFragment;


public enum FragmentUnit {

    MAP(0, GoogleMapFragment.class),
    START(1, StartFragment.class),
    SETTINGS(2, StartFragment.class),
    BADGES(3, StartFragment.class);

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
