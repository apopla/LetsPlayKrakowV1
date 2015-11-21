package com.example.kitowcy.letsplaykrakow.connection;

import android.app.Application;

import com.example.kitowcy.letsplaykrakow.BuildConfig;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.log.LogLevel;

/**
 * Created by lukasz on 21.11.15.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDependencies();
    }

    private void initializeDependencies() {
        KontaktSDK.initialize(this)
                .setDebugLoggingEnabled(BuildConfig.DEBUG)
                .setLogLevelEnabled(LogLevel.DEBUG, true)
                .setCrashlyticsLoggingEnabled(true);
    }
}
