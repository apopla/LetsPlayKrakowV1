package com.example.kitowcy.letsplaykrakow.connection;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marchuck on 22.11.15.
 */
public class Deserializer {

    public static final String TAG = Deserializer.class.getName();
    private Deserializer() {
    }

    public static KontaktEvent deserialize(Context context, JSONObject jsonObject) throws JSONException {
        String type = jsonObject.getString("type");
        JSONObject data = jsonObject.getJSONObject("data");
        String beaconType = data.getString("beaconType");
        String uniqueId = data.getString("uniqueId");
        if (beaconType.equals("event")) {
            String name = data.getString("name");
            String desc = data.getString("desc");
            String address = data.getString("address");
            double lat = data.getDouble("lat");
            double lng = data.getDouble("lng");
            return new KontaktEvent(beaconType, uniqueId, desc, address, name, lat, lng);
        }

        return new KontaktEvent(beaconType, uniqueId);
    }
}
