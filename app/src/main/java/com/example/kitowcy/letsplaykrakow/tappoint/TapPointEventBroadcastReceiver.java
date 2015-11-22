package com.example.kitowcy.letsplaykrakow.tappoint;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.adapters.FilterDialogBuilder;
import com.example.kitowcy.letsplaykrakow.connection.Deserializer;
import com.example.kitowcy.letsplaykrakow.connection.KontaktEvent;
import com.example.kitowcy.letsplaykrakow.data.Place;
import com.example.kitowcy.letsplaykrakow.entities.activities.PlaceActivity;
import com.proxama.tappoint.trigger.Trigger;
import com.proxama.tappoint.trigger.Triggers;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Arek on 22.11.2015.
 */
public class TapPointEventBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = TapPointEventBroadcastReceiver.class.getName();

    private static final String EVENT_BEACON_TYPE = "event";
    private static final String VALID_BEACON_TYPE = "valid";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive() called with: " + "context = [" + context + "], intent = [" + intent + "]");

        if (Triggers.ACTION_TRIGGERS_DETECTED.equals(intent.getAction())) {
            ArrayList<Trigger> triggers = intent.getParcelableArrayListExtra(Triggers.EXTRA_DETECTED_TRIGGERS);

            for(Trigger trigger: triggers) {
                Log.d(TAG, "Trigger json: " + trigger.getTriggerPayload());
                Log.d(TAG, "Trigger id: " + trigger.getTriggerId());
                Log.d(TAG, "Received " + triggers.size() + " events.");

                try {
                    KontaktEvent event = Deserializer.deserialize(context, triggers.get(0).getTriggerPayload());
                    if (event.getType().equals(EVENT_BEACON_TYPE)) {
                        showNotification(context, event);
                    } else if (event.getType().equals(VALID_BEACON_TYPE)) {
                        FilterDialogBuilder.buildNotification(context);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void showNotification(Context context, KontaktEvent kontaktEvent) {
        Intent intent = new Intent(context, PlaceActivity.class);
        intent.putExtra("NAME", kontaktEvent.getName());
        intent.putExtra("DESCRIPTION", kontaktEvent.getDesc());
        intent.putExtra("IMAGE_RES", R.drawable.alchemia);
        intent.putExtra("PLAY", false);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(context, 0,
                new Intent[] { intent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("You reach " + kontaktEvent.getName() + "!")
                .setContentText(kontaktEvent.getDesc())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
