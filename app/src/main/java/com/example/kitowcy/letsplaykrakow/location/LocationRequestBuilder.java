package com.example.kitowcy.letsplaykrakow.location;

import com.google.android.gms.location.LocationRequest;

import java.io.Serializable;

/**
 * Created by lukasz on 16.11.15.
 */
public class LocationRequestBuilder implements Serializable {
    /**
     * priority of accuracy
     */
    public static final int HIGH = LocationRequest.PRIORITY_HIGH_ACCURACY;
    public static final int BALANCED = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;
    public static final int LOW = LocationRequest.PRIORITY_LOW_POWER;
    public static final int NO = LocationRequest.PRIORITY_NO_POWER;

    int accuracy = HIGH;
    long updateInterval = 1000; ////milliseconds
    long fastestUpdateInterval = 500; //milliseconds

    public LocationRequestBuilder() {
    }

    public LocationRequestBuilder(int accuracy, long updateInterval, long fastestUpdateInterval) {
        withAccuracy(accuracy).withFastestInterval(updateInterval).withFastestInterval(fastestUpdateInterval);
    }

    public LocationRequestBuilder withAccuracy(int accuracy) {
        if (accuracy == HIGH || accuracy == BALANCED || accuracy == LOW || accuracy == NO)
            this.accuracy = accuracy;
        return this;
    }

    public LocationRequestBuilder withUpdateInterval(long millis) {
        if (updateInterval > 0)
            this.updateInterval = millis;
        return this;
    }

    public LocationRequestBuilder withFastestInterval(long millis) {
        if (0 < millis && millis <= updateInterval)
            this.fastestUpdateInterval = millis;
        else
            this.fastestUpdateInterval = this.updateInterval / 2;
        return this;
    }

    public LocationRequest build() {
        LocationRequest locationRequest = new LocationRequest();

        locationRequest.setPriority(this.accuracy);

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        locationRequest.setInterval(this.updateInterval);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        locationRequest.setFastestInterval(this.fastestUpdateInterval);

        return locationRequest;
    }
}
