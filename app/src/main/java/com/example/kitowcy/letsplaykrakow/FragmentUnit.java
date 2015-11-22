package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;

import com.example.kitowcy.letsplaykrakow.entities.fragments.AchievementsFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.GoogleMapFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.GridViewFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.PlacesFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.RoutesFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.SchematFragment;
import com.example.kitowcy.letsplaykrakow.entities.fragments.StartFragment;


public enum FragmentUnit {

    SPLASH(-1, SchematFragment.class),
    MAP(0, GoogleMapFragment.class),
    ROUTES(1, RoutesFragment.class),
    ACHIEVEMENTS(2, GridViewFragment.class),
    RECYCLER(3, GridViewFragment.class),

    PLACES(4, PlacesFragment.class);


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
