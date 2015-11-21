package com.example.kitowcy.letsplaykrakow.beacon;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Arek on 21.11.2015.
 */
public class KontaktBeaconService extends Service {

    public KontaktBeaconService() {
        super();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
