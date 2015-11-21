package com.example.kitowcy.letsplaykrakow.location;

import android.app.Service;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.kitowcy.letsplaykrakow.Constants;
import com.example.kitowcy.letsplaykrakow.entities.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Lukasz Marczak on 2015-06-25.
 * broadcasts position on map
 */
public class LocationService extends Service implements LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult> {

    /**
     * Constant used in the location settings dialog.
     */
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    /**
     * String constant very helpful for debugging
     */
    private static final String TAG = LocationService.class.getSimpleName();

    /**
     * Singleton pattern: we are providing only one instance of service,
     * we can access to change fields and invoke methods
     */
    private static LocationService instance = null;

    /**
     * Geocoder instance needed for getting advanced address info
     */
    private Geocoder geocoder;

    /**
     * Google API client which enables connection
     */
    private GoogleApiClient googleApiClient = null;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    protected LocationRequest locationRequest;

    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */
    protected LocationSettingsRequest locationSettingsRequest;

    /**
     * Realm Instance which provides storing detected coordinates into database
     */
    private Realm realm;

    /**
     * if we want to start tracking path(start to save detected points in database),
     * we set this flag true,
     */
    public static boolean trackingEnabled = false;

    /**
     * Getters & Setters
     */

    public static LocationService getInstance() {
        return instance;
    }

    /**
     * when we call
     * we can launch service with different location update options
     */
    private LocationRequestBuilder locationRequestBuilder = null;
//
    /**
     * Tracks the status of the location updates request. Value changes when the user presses the
     * Start Updates and Stop Updates buttons.
     */
    protected Boolean mRequestingLocationUpdates;

    /**
     * Override methods
     */

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        instance = this;
        buildGoogleApiClient();
        createDefaultLocationRequest();
        geocoder = new Geocoder(this, Locale.getDefault());
        realm = Realm.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand(intent is "
                + ((intent == null) ? "null" : "not null") + ", flags = " + flags + ", startId = " + startId);
        //stop previous connection
        disconnectGoogleApiClient();

        //if new bulider exists, start connection with new settings
        if (intent != null && intent.getExtras() != null) {
            Log.d(TAG, "extras not null");
            LocationRequestBuilder builder =
                    (LocationRequestBuilder) intent.getExtras().getSerializable("LOCATION_REQUEST_BUILDER");
            if (builder != null) {
                Log.d(TAG, "set new requestBuilder");
                this.locationRequestBuilder = builder;
            }
        } else if (this.locationRequestBuilder == null) {
            Log.d(TAG, "creating default requestBuilder");
            locationRequestBuilder = new LocationRequestBuilder();
        }
        //overwrite new locationRequest
        this.locationRequest = locationRequestBuilder.build();
        //connect with new settings
        connectGoogleApiClient();
        buildLocationSettingsRequest();
        checkLocationSettings();
        return Service.START_NOT_STICKY;
    }


    /**
     * Check if the device's location settings are adequate for the app's needs using the
     * {@link com.google.android.gms.location.SettingsApi#checkLocationSettings(GoogleApiClient,
     * LocationSettingsRequest)} method, with the results provided through a {@code PendingResult}.
     */
    protected void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        googleApiClient,
                        locationSettingsRequest
                );
        result.setResultCallback(this);
    }

    /**
     * The callback invoked when
     * {@link com.google.android.gms.location.SettingsApi#checkLocationSettings(GoogleApiClient,
     * LocationSettingsRequest)} is called. Examines the
     * {@link LocationSettingsResult} object and determines if
     * location settings are adequate. If they are not, begins the process of presenting a location
     * settings dialog to the user.
     */
    @Override
    public void onResult(LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                Log.i(TAG, "All location settings are satisfied.");
                startLocationUpdates();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                Log.i(TAG, "Location settings are not satisfied. Show the user a dialog to" +
                        "upgrade location settings ");
                try {
                    // Show the dialog by calling startResolutionForResult(), and check the result
                    // in onActivityResult().
                    status.startResolutionForResult(MainActivity.getInstance(), REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    Log.i(TAG, "PendingIntent unable to execute request.");
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog " +
                        "not created.");
                break;
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.d(TAG, "onTaskRemoved with intent: " + rootIntent.getAction());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        stopLocationUpdates();
        disconnectGoogleApiClient();
        super.onDestroy();
    }

//    private void saveLocation(@NonNull Location location) {
//        if (trackingEnabled) {
//            Log.d(TAG, "tracking enabled");
//            try {
//                realm.beginTransaction();
//                Position position = realm.createObject(Position.class);
//                position.setUuid(UUID.randomUUID().toString());
//                position.setLatitude(location.getLatitude());
//                position.setLongitude(location.getLongitude());
//                position.setTime(System.currentTimeMillis());
//            } catch (Exception ex) {
//                Log.e(TAG, "broken realm transaction!!!");
//                realm.cancelTransaction();
//            } finally {
//                try {
//                    realm.commitTransaction();
//                } catch (Exception ex) {
//                    Log.e(TAG, "failed to commit transaction ");
//                }
//                Log.i(TAG, "successfully saved (" + location.getLatitude() + "," + location.getLongitude() + ")");
//            }
//        }
//    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged()" + ((location == null) ? "null location!" : ""));
        if (location != null) {
            //check for debug mode (used mainly for demos and off-site development)
            //save new location as last known
            LocationData.setCurrentLocation(location);
            //send location update broadcast
            sendLocationUpdateBroadcast(location);
//            saveLocation(location);
        }
    }


    private LatLng getPositionFromLocation(Location location) {
        return location != null ? new LatLng(location.getLatitude(), location.getLongitude()) : null;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected()");
//        LocationServices.GeofencingApi
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

//        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, new LocationCallback() {
//            @Override
//            public void onLocationResult(LocationResult result) {
//                super.onLocationResult(result);
//                Log.d(TAG, "onLocationResult " + result.getLastLocation());
//            }
//
//            @Override
//            public void onLocationAvailability(LocationAvailability locationAvailability) {
//                super.onLocationAvailability(locationAvailability);
//                Log.d(TAG, "onLocationAvailability " + locationAvailability.isLocationAvailable());
//                if(!locationAvailability.isLocationAvailable()){
//
//                }
//            }
//        }, Looper.getMainLooper());

        if (lastLocation != null) {
            getAddressObservable(getPositionFromLocation(lastLocation)).subscribe(new Action1<Address>() {
                @Override
                public void call(Address address) {
                    Log.d(TAG, "new address: " + address.getExtras());
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Log.e(TAG, "error occurred");
                    Log.e(TAG, throwable.getMessage());
                    throwable.printStackTrace();
                }
            });

            LocationData.setCurrentLocation(lastLocation);
            Log.d(TAG, "last detected location : " + lastLocation);
            sendLocationUpdateBroadcast(lastLocation);
        } else {
            Log.e(TAG, "last location is null");
        }
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended(" + i + ")");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "onConnectionFailed(" + connectionResult.getErrorCode() + ")");
    }

    private void buildGoogleApiClient() {
        Log.d(TAG, "buildGoogleApiClient()");
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void createDefaultLocationRequest() {
        Log.d(TAG, "createDefaultLocationRequest()");
        locationRequest = new LocationRequest();
        locationRequest.setInterval(Constants.LOCATION_UPDATE_INTERVAL);
        locationRequest.setFastestInterval(Constants.LOCATION_UPDATE_FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(1.0f);
    }

    /**
     * Uses a {@link LocationSettingsRequest.Builder} to build
     * a {@link LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    protected void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        locationSettingsRequest = builder.build();
    }

//    protected void startLocationUpdates() {
//        Log.d(TAG, "startLocationUpdates()");
//        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
//    }

    /**
     * Requests location updates from the FusedLocationApi.
     */
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient,
                locationRequest,
                this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                mRequestingLocationUpdates = true;
//                setButtonsEnabledState();
            }
        });
    }


    protected void stopLocationUpdates() {
        Log.d(TAG, "stopLocationUpdates()");
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    private void sendLocationUpdateBroadcast(Location location) {
        Log.d(TAG, "sendLocationUpdateBroadcast()" + (location == null ? "null location" : ""));
        Intent intent = new Intent(Constants.LOCATION_UPDATE_BROADCAST);
        intent.putExtra(Constants.LOCATION_UPDATE_KEY, location);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void connectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    public void disconnectGoogleApiClient() {
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    /**
     * Terminates connection and stops Location Service
     */
    public static void stop() {
        Log.d(TAG, "stopService ");
        if (instance != null) {
            instance.stopLocationUpdates();
            instance.disconnectGoogleApiClient();
            instance.stopSelf();
            instance = null;
        }
    }

    /**
     * Asynchronus execution: get readable info in which place we are currently in
     *
     * @param position - LatLng object represents current geographical position
     * @return - rx.Observable<Address> which can be further consumed by subscriber
     */
    @NonNull
    public static Observable<Address> getAddressObservable(@NonNull final LatLng position) {
        return Observable.create(new Observable.OnSubscribe<Address>() {
            @Override
            public void call(Subscriber<? super Address> subscriber) {
                if (instance.geocoder == null) {
                    subscriber.onError(new Throwable("GeoCoder is null!"));
                } else {
                    List<Address> addresses = null;
                    try {
                        addresses = instance.geocoder.getFromLocation(position.latitude, position.longitude, 1);
                    } catch (IOException ioException) {
                        Log.e(TAG, "getAddress : returning null addresses");
                        ioException.printStackTrace();
                    } finally {
                        if (addresses != null && addresses.size() > 0) {
                            for (Address a : addresses) {
                                Log.i(TAG, "address : " + a.toString());
                            }
                        }
                    }
                    if (addresses == null)
                        subscriber.onError(new Throwable("Returned null address!!"));
                    else subscriber.onNext(addresses.get(0));
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());
    }
}