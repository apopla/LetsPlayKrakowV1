package com.example.kitowcy.letsplaykrakow.location;

import android.location.Location;
import android.util.Log;

import com.example.kitowcy.letsplaykrakow.Constants;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by Lukasz Marczak on 2015-07-27.
 */
public class LocationData {
    private static final String TAG = LocationData.class.getSimpleName();
    private static final String DEFAULT_PROVIDER = "DEFAULT";

    private static Location currentLocation;
    private static Location lastLocation;


    public static Location getCurrentLocation() {
        if (lastLocation == null)
            lastLocation = currentLocation;
        if (currentLocation == null) {
            Location defaultLocation = new Location(DEFAULT_PROVIDER);
            defaultLocation.setLatitude(Constants.DEFAULT_LOCATION_LATITUDE);
            defaultLocation.setLongitude(Constants.DEFAULT_LOCATION_LONGITUDE);
            currentLocation = defaultLocation;
            return defaultLocation;
        }
        return currentLocation;
    }

    public static void setCurrentLocation(Location currentLocation) {
        String shortenLatitude = String.format("%.3f", currentLocation.getLatitude());
        String shortenLongitude = String.format("%.3f", currentLocation.getLongitude());
        LocationData.lastLocation = LocationData.currentLocation;
        Log.d(TAG, "setCurrentLocation(" + shortenLatitude + ", " + shortenLongitude + ")");
        LocationData.currentLocation = currentLocation;
    }

    public static LatLng getCurrentPosition() {
        if (currentLocation == null) {
            currentLocation = getCurrentLocation();
        }
        return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
    }

    public static boolean locationChanged() {
        if (currentLocation == null || lastLocation == null)
            return true;
        if (lastLocation.getLatitude() == currentLocation.getLatitude())
            if (lastLocation.getLongitude() == currentLocation.getLongitude())
                return false;
        return true;
    }
}
