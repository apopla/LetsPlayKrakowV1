package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;


/**
 *
 * Created by Arek Biela on 2015-09-04.
 */
public enum FragmentUnit {

    START (0, StartFragment.class),
    SETTINGS (1, StartFragment.class),
    BADGES(2, StartFragment.class);

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
