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
import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.ble.discovery.ibeacon.IBeaconDeviceEvent;
import com.kontakt.sdk.android.ble.filter.ibeacon.IBeaconFilter;
import com.kontakt.sdk.android.ble.filter.ibeacon.IBeaconFilters;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Arek on 21.11.2015.
 */
public class KontaktBeaconService extends Service implements ProximityManager.ProximityListener {

    private static final String TAG = KontaktBeaconService.class.getName();

    private static final String AREK_BLACK_BEACON_UNIQUE_UUID = "Sgwx";
    private static final String AREK_WHITE_BEACON_UNIQUE_UUID = "cIKQ";

    private static final int BEACON_MONITORING_TIME = 3;
    private static final int BEACON_RANGING_TIME = 2;

    private ProximityManager beaconManager;
    private List<IBeaconFilter> filteredBeaconList;
    private IBeaconScanContext iBeaconScanContext;
    private ScanContext scanContext;

    public KontaktBeaconService() {
        super();
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() called with: " + "");

        super.onCreate();
        initializeBeaconFilter();
        initializeIBeaconScanContext();
        initializeScanContext();
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

    private void initializeBeaconFilter() {
        Log.d(TAG, "initializeBeaconFilter() called with: " + "");

        filteredBeaconList = new ArrayList<>();
        filteredBeaconList.add(IBeaconFilters.newUniqueIdFilter(AREK_BLACK_BEACON_UNIQUE_UUID));
        filteredBeaconList.add(IBeaconFilters.newUniqueIdFilter(AREK_WHITE_BEACON_UNIQUE_UUID));
    }

    private void initializeIBeaconScanContext() {
        Log.d(TAG, "initializeIBeaconScanContext() called with: " + "");

        iBeaconScanContext = new IBeaconScanContext.Builder()
                .setEventTypes(EnumSet.of(EventType.DEVICE_DISCOVERED, EventType.DEVICE_LOST, EventType.DEVICES_UPDATE, EventType.SPACE_ABANDONED, EventType.SPACE_ENTERED))
                .setIBeaconFilters(filteredBeaconList)
                .build();
    }

    private void initializeScanContext() {
        Log.d(TAG, "initializeScanContext() called with: " + "");

        scanContext = new ScanContext.Builder()
                .setScanMode(ProximityManager.SCAN_MODE_BALANCED)
                .setActivityCheckConfiguration(ActivityCheckConfiguration.DEFAULT)
                .setForceScanConfiguration(ForceScanConfiguration.DEFAULT)
                .setIBeaconScanContext(iBeaconScanContext)
                .setScanPeriod(new ScanPeriod(TimeUnit.SECONDS.toMillis(BEACON_MONITORING_TIME), TimeUnit.SECONDS.toMillis(BEACON_RANGING_TIME)))
                .build();
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

        final IBeaconDeviceEvent iBeaconDeviceEvent = (IBeaconDeviceEvent) event;

        switch(event.getEventType()) {

            case DEVICES_UPDATE: {

                for (IBeaconDevice beaconDevice : iBeaconDeviceEvent.getDeviceList()) {
                    Log.d(TAG, "Device discovered !");
                    Log.d(TAG, "Beacon ranging: major: " + beaconDevice.getMajor());
                    Log.d(TAG, "Beacon ranging: minor: " + beaconDevice.getMinor());
                    Log.d(TAG, "Beacon ranging: unique id: " + beaconDevice.getUniqueId());
                }
            }
            break;
        }
    }
}
