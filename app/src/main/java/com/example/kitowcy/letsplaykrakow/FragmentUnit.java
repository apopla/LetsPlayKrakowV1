package com.example.kitowcy.letsplaykrakow;

import android.support.v4.app.Fragment;

import pl.sointeractive.smartcity.fragments.information.InformationFragment;
import pl.sointeractive.smartcity.fragments.notifications.AllNotificationsFragment;
import pl.sointeractive.smartcity.fragments.trip.MyJourneysFragment;
import pl.sointeractive.smartcity.fragments.offers.TestMyOffersFragment;
import pl.sointeractive.smartcity.fragments.profile.ProfileEditFragment;
import pl.sointeractive.smartcity.fragments.profile.ProfileHolderFragment;
import pl.sointeractive.smartcity.fragments.settings.SettingsHolderFragment;
import pl.sointeractive.smartcity.fragments.timetable.StopLineFragment;
import pl.sointeractive.smartcity.fragments.timetable.TimetableBusOperatorsFragment;
import pl.sointeractive.smartcity.fragments.timetable.TimetableFragment;

/**
 *
 * Created by Arek Biela on 2015-09-04.
 */
public enum FragmentUnit {

    JOURNEYS (0, MyJourneysFragment.class),
    TIMETABLE (1, TimetableFragment.class),
    CITYMAP (2, TimetableBusOperatorsFragment.class),
    OFFERS (3, TestMyOffersFragment.class),
    COMMUNITY (4, CommunityFragment.class),
    PROFILE (5, ProfileHolderFragment.class),
    SETTINGS(6, SettingsHolderFragment.class),
    PROFILE_EDIT(7, ProfileEditFragment.class),
    START_SCREEN(12, StartScreenFragment.class),
    INFORMATION(13, InformationFragment.class),
    TIMETABLE_BUS_OPERATORS(28, TimetableBusOperatorsFragment.class),
    NOTIFICATIONS(100, AllNotificationsFragment.class),
    BUS_STOP_LINES(31, StopLineFragment.class);

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
