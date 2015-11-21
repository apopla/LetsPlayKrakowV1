package com.example.kitowcy.letsplaykrakow.connection;

import android.app.Application;
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
//        KontaktSDK.initialize(this)
//                .setDebugLoggingEnabled(BuildConfig.DEBUG)
//                .setLogLevelEnabled(LogLevel.DEBUG, true)
//                .setCrashlyticsLoggingEnabled(true);
    }
}
