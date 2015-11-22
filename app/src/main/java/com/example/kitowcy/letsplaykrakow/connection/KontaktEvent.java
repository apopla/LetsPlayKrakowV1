package com.example.kitowcy.letsplaykrakow.connection;

import android.util.Log;

/**
 * Created by lukasz on 22.11.15.
 */
public class KontaktEvent {
    public static final String TAG = KontaktEvent.class.getSimpleName();
    private String type;
    private String uniqueId;

    public KontaktEvent(String type, String uniqueId) {
        Log.d(TAG, "KontaktEvent " + type + "," + uniqueId);
        this.type = type;
        this.uniqueId = uniqueId;
    }
}
