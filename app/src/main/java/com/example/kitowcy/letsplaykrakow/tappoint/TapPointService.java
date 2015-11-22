package com.example.kitowcy.letsplaykrakow.tappoint;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.proxama.tappoint.auth.AuthListener;
import com.proxama.tappoint.auth.Authentication;
import com.proxama.tappoint.error.ApiError;
import com.proxama.tappoint.sync.SyncListener;
import com.proxama.tappoint.sync.SyncResult;
import com.proxama.tappoint.sync.Synchronisation;
import com.proxama.tappoint.trigger.Triggers;

/**
 * Created by Arek on 22.11.2015.
 */
public class TapPointService extends Service implements AuthListener, SyncListener {

    private static final String TAG = TapPointService.class.getName();
    private static final String APP_NAME = "khlizardman";

    private TapPointEventBroadcastReceiver tapPointEventBroadcastReceiver = new TapPointEventBroadcastReceiver();

    public TapPointService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Authentication.getAuthManager(this).authenticate(APP_NAME, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Triggers.getTriggersManager(this).stopMonitoring();
    }

    @Override
    public void onAuthSuccess() {
        Log.d(TAG, "Authentication successful !");
        Synchronisation.getSyncManager(this).synchronise(TapPointService.this);
    }

    @Override
    public void onAuthFailure(ApiError apiError) {

    }

    @Override
    public void onSyncSuccess(SyncResult syncResult) {
        Log.d(TAG, "Sync successful !");
        Triggers.getTriggersManager(this).startMonitoring();

    }

    @Override
    public void onSyncFailure(ApiError apiError) {
        Log.d(TAG, "Sync failed !");
    }
}
