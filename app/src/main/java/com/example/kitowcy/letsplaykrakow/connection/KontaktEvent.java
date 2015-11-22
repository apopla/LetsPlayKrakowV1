package com.example.kitowcy.letsplaykrakow.connection;

import android.util.Log;

/**
 * Created by lukasz on 22.11.15.
 */
public class KontaktEvent {
    public static final String TAG = KontaktEvent.class.getSimpleName();
    private String type;
    private String uniqueId;
    private String desc;
    private String address;
    private String name;
    private double lat;
    private double lng;


    public KontaktEvent(String type, String uniqueId) {
        Log.d(TAG, "KontaktEvent " + type + "," + uniqueId);
        this.type = type;
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public KontaktEvent(String type, String uniqueId, String desc, String address, String name, double lat, double lng) {
        Log.d(TAG, "KontaktEvent() called with: " + "type = [" + type + "], uniqueId = [" + uniqueId + "], desc = [" + desc + "], address = [" + address + "], lat = [" + lat + "], lng = [" + lng + "]");

        this.type = type;
        this.uniqueId = uniqueId;
        this.desc = desc;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
