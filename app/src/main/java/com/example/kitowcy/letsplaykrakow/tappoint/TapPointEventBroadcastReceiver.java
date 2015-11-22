package com.example.kitowcy.letsplaykrakow.tappoint;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.proxama.tappoint.trigger.Trigger;
import com.proxama.tappoint.trigger.Triggers;

import java.util.ArrayList;

/**
 * Created by Arek on 22.11.2015.
 */
public class TapPointEventBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = TapPointEventBroadcastReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: " + "context = [" + context + "], intent = [" + intent + "]");

        if (Triggers.ACTION_TRIGGERS_DETECTED.equals(intent.getAction())) {
            ArrayList<Trigger> triggers = intent.getParcelableArrayListExtra(Triggers.EXTRA_DETECTED_TRIGGERS);

            Log.d(TAG, "Trigger json: " + triggers.get(0).getTriggerPayload().toString());
            Log.d(TAG, "Trigger id: " + triggers.get(0).getTriggerId());
            Log.d(TAG, "Received " + triggers.size() + " events.");
        }


    }
}
