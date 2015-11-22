package com.example.kitowcy.letsplaykrakow.connection;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marchuck on 22.11.15.
 */
public class Deserializer {
    private Deserializer() {
    }

    public static KontaktEvent deserialize(Context context, JSONObject jsonObject) throws JSONException {
        String type = jsonObject.getString("type");
        JSONObject data = jsonObject.getJSONObject("data");
        String beaconType = data.getString("beaconType");
        String uniqueId = data.getString("uniqueId");

        return new KontaktEvent(beaconType, uniqueId);
    }
}
