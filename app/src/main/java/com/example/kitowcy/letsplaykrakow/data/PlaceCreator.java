package com.example.kitowcy.letsplaykrakow.data;

import android.content.Context;

import com.example.kitowcy.letsplaykrakow.R;

import java.util.UUID;

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

        String kolankoName = "Kolanko no.6";
        String kolankoDescription = "Kolanko has long specialised in pancakes (nalesniki), with a wide variety of fillings to choose from. However, recently their menu was expanded by the menu is a selection of world cuisine samples, such as Greek musaka, Spanish paella, Moroccan tagine, Indonesian satay and more. One thing definitely worth recommending is Kolanko's rich breakfast buffet offer, with cold and hot foods. Also, Kolanko No. 6 is somewhat of a cultural hub, so make sure to check if there's any exhibition, concert, film screening, or a meeting with a traveller coming up, in order not to miss it.";
        String kolankoAddress = "ul. Jozefa 17";
        Double kolankoLat = 50.050947;
        Double kolankoLng = 19.946196;

        String staraSynagogaName = "The old Synagogue";
        String staraSynagogaDescription = "Krakow's oldest synagogue was established shortly after the transfer of Jews from the area of the Market Square to the city of Kazimierz (15th c.). Down the centuries it was repeatedly destroyed by fires, but suffered most damage during World War II. After renovation in the 1950s, the Jewish Commune donated the property to the Historical Museum of the City of Krakow to arrange a permanent exhibition on the history and culture of Krakow Jews";
        String staraSynagogaAddress = "ul. Bartosza";
        Double staraSynagogaLat = 50.051420;
        Double staraSynagogaLng = 19.948415;

        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        Place alchemiaPlace = realm.createObject(Place.class);
        alchemiaPlace.setUUID(UUID.randomUUID().toString());
        alchemiaPlace.setName(alchemiaName);
        alchemiaPlace.setCategory(context.getResources().getString(R.string.fun_category));
        alchemiaPlace.setImageResourceId(R.drawable.alchemia);
        alchemiaPlace.setDescription(alchemiaDescription);
        alchemiaPlace.setAddress(alchemiaAddress);
        alchemiaPlace.setLatitude(alchemiaLat);
        alchemiaPlace.setLongitude(alchemiaLng);
        alchemiaPlace.setLetsPlayKrakow(false);

        Place kolankoPlace = realm.createObject(Place.class);
        kolankoPlace.setUUID(UUID.randomUUID().toString());
        kolankoPlace.setName(kolankoName);
        kolankoPlace.setCategory(context.getResources().getString(R.string.food_category));
        kolankoPlace.setImageResourceId(R.drawable.kolanko);
        kolankoPlace.setDescription(kolankoDescription);
        kolankoPlace.setAddress(kolankoAddress);
        kolankoPlace.setLatitude(kolankoLat);
        kolankoPlace.setLongitude(kolankoLng);
        kolankoPlace.setLetsPlayKrakow(false);

        Place staraSynagogaPlace = realm.createObject(Place.class);
        staraSynagogaPlace.setUUID(UUID.randomUUID().toString());
        staraSynagogaPlace.setName(staraSynagogaName);
        staraSynagogaPlace.setCategory(context.getResources().getString(R.string.monument_category));
        staraSynagogaPlace.setImageResourceId(R.drawable.synagoga);
        staraSynagogaPlace.setDescription(staraSynagogaDescription);
        staraSynagogaPlace.setAddress(staraSynagogaAddress);
        staraSynagogaPlace.setLatitude(staraSynagogaLat);
        staraSynagogaPlace.setLongitude(staraSynagogaLng);
        staraSynagogaPlace.setLetsPlayKrakow(true);



     /*   realm.copyToRealm(alchemiaPlace);
        realm.copyToRealm(kolankoPlace);
        realm.copyToRealm(staraSynagogaPlace);*/

        realm.commitTransaction();



    }
}
