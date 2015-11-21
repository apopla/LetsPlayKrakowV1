package com.example.kitowcy.letsplaykrakow.data;

import android.content.Context;

import com.example.kitowcy.letsplaykrakow.R;

import io.realm.Realm;

/**
 * Created by lukasz on 21.11.15.
 * Hardcode places here, @apopla
 */
public class PlaceCreator {

    private PlaceCreator() {
    }
    public static void load(Context context){
        //// TODO: 21.11.15
        String alchemiaName = "Alchemia";
        String alchemiaDescription = "Cracow's legendary pub, place of many interesting events. Unforgettable design, atmosphere\tand people. In summer, outdoor tables are available.";
        String alchemiaAddress = "ul. Estery 5 ";
        Double alchemiaLat = 50.052316;
        Double alchemiaLng = 19.944859;

        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        Place alchemiaPlace = new Place();
        alchemiaPlace.setName(alchemiaName);
        alchemiaPlace.setCategory(context.getResources().getString(R.string.fun_category));
        alchemiaPlace.setDescription(alchemiaDescription);
        alchemiaPlace.setAddress(alchemiaAddress);
        alchemiaPlace.setLatitude(alchemiaLat);
        alchemiaPlace.setLongitude(alchemiaLng);
        alchemiaPlace.setLetsPlayKrakow(false);

        Place kolan

    }
}
