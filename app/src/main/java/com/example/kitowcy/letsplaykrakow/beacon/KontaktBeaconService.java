package com.example.kitowcy.letsplaykrakow.beacon;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;

import com.example.kitowcy.letsplaykrakow.R;
import com.example.kitowcy.letsplaykrakow.data.Place;
import com.example.kitowcy.letsplaykrakow.entities.activities.PlaceActivity;
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
import com.proxama.tappoint.auth.Authentication;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Arek on 21.11.2015.
 */
public class KontaktBeaconService extends Service implements ProximityManager.ProximityListener {

    private static final String TAG = KontaktBeaconService.class.getName();

    private static final String AREK_BLACK_BEACON_UNIQUE_UUID = "Sgwx";
    private static final String AREK_WHITE_BEACON_UNIQUE_UUID = "cIKQ";
    private static final String BEACON_TRAM = "ElKv";

    private static final int BEACON_MONITORING_TIME = 3;
    private static final int BEACON_RANGING_TIME = 2;

    private boolean isNotificationShown = false;

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
        filteredBeaconList.add(IBeaconFilters.newUniqueIdFilter(BEACON_TRAM));
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

                    if (beaconDevice.getUniqueId().equals(BEACON_TRAM)) {
                        if (!isNotificationShown) {
                            showTramNotification();
                            isNotificationShown = true;
                        }
                    }
//                    Realm realm = Realm.getInstance(getApplicationContext());
//                    Place place = realm.where(Place.class).equalTo("UUID", beaconDevice.getUniqueId()).findFirst();
//                    if(!place.isSeen()) {
//                        realm.beginTransaction();
//                        place.setIsSeen(true);
//                        realm.copyToRealmOrUpdate(place);
//                        realm.commitTransaction();

//                        showNotification(place);
//                   }
                }
            }
            break;
        }
    }

    private void showTramNotification() {

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.bekon)
                .setContentTitle("Get off the bus on the next station !")
                .setContentText("2 places are near you !")
                .setAutoCancel(true)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2, notification);
    }

    public void showNotification(Place place) {
            Intent intent = new Intent(getApplicationContext(), PlaceActivity.class);
            intent.putExtra("NAME", place.getName());
            intent.putExtra("DESCRIPTION", place.getDescription());
            intent.putExtra("IMAGE_RES", place.getImageResourceId());
            intent.putExtra("PLAY", place.isLetsPlayKrakow());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { intent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.bekon)
                .setContentTitle("You reached " + place.getName() + "!")
                .setContentText(place.getDescription())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

}