package com.example.kitowcy.letsplaykrakow.beacon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.kontakt.sdk.android.ble.configuration.ActivityCheckConfiguration;
import com.kontakt.sdk.android.ble.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.ble.configuration.ScanPeriod;
import com.kontakt.sdk.android.ble.configuration.scan.IBeaconScanContext;
import com.kontakt.sdk.android.ble.configuration.scan.ScanContext;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.discovery.BluetoothDeviceEvent;
import com.kontakt.sdk.android.ble.manager.ProximityManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by Arek on 21.11.2015.
 */
public class KontaktBeaconService extends Service implements ProximityManager.ProximityListener {

    private static final String TAG = KontaktBeaconService.class.getName();

    private ProximityManager beaconManager;

    private IBeaconScanContext iBeaconScanContext;

    /**
     * Scan context
     */
    private ScanContext scanContext = new ScanContext.Builder()
            .setScanMode(ProximityManager.SCAN_MODE_BALANCED)
            .setActivityCheckConfiguration(ActivityCheckConfiguration.DEFAULT)
            .setForceScanConfiguration(ForceScanConfiguration.DEFAULT)
            .setIBeaconScanContext(IBeaconScanContext.DEFAULT)
            .setScanPeriod(new ScanPeriod(TimeUnit.SECONDS.toMillis(7), TimeUnit.SECONDS.toMillis(3)))
            .build();


    public KontaktBeaconService() {
        super();
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() called with: " + "");

        super.onCreate();
        initializeProximityManager();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: " + "intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: " + "intent = [" + intent + "]");

        return null;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called with: " + "");

        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
    }

    private void initializeProximityManager() {
        Log.d(TAG, "initializeProximityManager() called with: " + "");

        beaconManager = new ProximityManager(this);
        beaconManager.initializeScan(scanContext, new OnServiceReadyListener() {

            @Override
            public void onServiceReady() {
                Log.d(TAG, "onCreate(): onBeaconServiceReady !");
                beaconManager.attachListener(KontaktBeaconService.this);
            }

            @Override
            public void onConnectionFailure() {
                Log.d(TAG, "onCreate(): onConnectionFailure !");
            }
        });
    }

    @Override
    public void onScanStart() {
        Log.d(TAG, "Beacon scanning started !");
    }

    @Override
    public void onScanStop() {
        Log.d(TAG, "Beacon scanning stopped !");
    }

    @Override
    public void onEvent(BluetoothDeviceEvent event) {
        Log.d(TAG, "onEvent() called with: " + "event = [" + event + "]");
    }
}
