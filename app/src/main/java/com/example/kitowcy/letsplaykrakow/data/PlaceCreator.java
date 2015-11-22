package com.example.kitowcy.letsplaykrakow.data;

import android.content.Context;

import com.example.kitowcy.letsplaykrakow.R;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

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

        String pierozkiName = "Pierozki u Vincenta";
        String pierozkiDescription = "The speciality, as the restaurant name suggests, are a whole variety of Polish dumplings (pierogi), hand-made little dough parcels with delicious fillings that are highly popular, traditional Polish fare. Normally you'd expect just a few varieties to choose from, but this cosy place has concocted dozens of varieties, such as with chicken and pineapple and some vegetarian versions like the popular spinach and mozzarella, a variety of fruits or dried prunes. They also rustle up the special 'Vincent' dumpling with meat and lentils. Open daily from noon til evening, they also gladly do take-aways.";
        String pierozkiAddress = "ul. Bozego Ciala 12";
        Double pierozkiLat = 50.051590;
        Double pierozkiLng = 19.943569;

        String singerName = "Singer";
        String singerDescription = "A Kazimierz legend - dim and gloomy, with shadows deep enough to drown the moodiest drinker, and illumination provided by an all-day candle vigil. Come in off the Jewish Square (Plac Zydowski) for a warming vodka or warm beer with fruit syrup, and lose track of the time altogether. Don't expect any live vocals, except in the early hours from an uproarious crowd - the bar is actually named after the Singer sewing machines and trestle tables scattered about the place. Classic Cracow.";
        String singerAddress = "ul. Estery 20";
        Double singerLat = 50.051184;
        Double singerLng = 19.945794;

        String flowerPowerName = "Flower Power";
        String flowerPowerDescription = "There will be waiting for you things like water pipes, soft pillows, sounds, smells, food. Peace & Love. Our arange sopu with carrot won Soup festival in 2005. First private, free hot spot!";
        String flowerPowerAddress = "ul. Nowa 3";
        Double flowerPowerLat = 50.051302;
        Double flowerPowerLng = 19.944552;

        String omertaName = "Omerta";
        String omertaDescription = "God Father themed pub, serving several regional beers. ";
        String omertaAddress = "ul. Kupa 3";
        Double omertaLat = 50.052063;
        Double omertaLng = 19.945772;

        String sasiedziName = "Restauracja Sasiedzi";
        String sasiedziDescription = "Our chefs are specializing in traditional Polish dishes, dinners and unique desserts. Thanks to their passion and inexhaustible creativity dishes served by professional waiters, are memorable – those which everybody wants to come back for. By Sąsiedzi Restaurant you can feel the taste of pleasure and warm, friendly atmosphere.";
        String sasiedziAddress = "ul. Miodowa 25";
        Double sasiedziLat = 50.051598;
        Double sasiedziLng = 19.942651;

        String remuhName = "Remuh synagogue and cementary";
        String remuhDescription = "The synagogue was erected in the sixteenth century for Moses Isserles, aka Remuh, the rabbi of Krakow and the famous codifier of Jewish law. Inside, visitors can see the Aron Kodesh (the Torah ark), the bimah - a place for reading the Torah and original paintings. Found at the cemetery adjacent to the synagogue in the 1950's were approx. 700 tombstones from the period of fourteenth to nineteenth century. Jews from all over the world come here to visit the tomb of Moses Isserles";
        String remuhAddress = "ul. Jakuba 4";
        Double remuhLat = 50.052320;
        Double remuhLng = 19.946765;

        String isaacName = "Isaac's synagogue";
        String isaacDescription = "Built with the consent of King Władysław IV Vasa in the seventeenth century, it is the largest synagogue located in Kazimierz. Its austere interior is decorated with early Baroque stucco. After World War II the temple was left in disrepair, until the late 80's when it was thoroughly renovated. The building was handed over to the Jewish Commune, who leased the synagogue to one of the Hasidic communities in 2007 for cult purposes.";
        String isaacAddress = "ul. Estery";
        Double isaacLat = 50.051559;
        Double isaacLng = 19.946601;

        String corpusChristiName = "Corpus Christi Church";
        String corpusChristiDescription = "The church was founded as the main temple of the newly founded city of Kazimierz; its construction began in 1340. Since the beginning of the fifteenth century the church remains under the care of the Canons Regular of the Lateran. Almost all of the temple's original equipment was destroyed during the Swedish invasion in 1655. As a result, the Gothic church received rich, baroque features, the most impressive of which is the pulpit, sculpted into a boat supported by mermaids and dolphins. Inside the church buried is Bartolomeo Berrecci, a native of Florence, architect and sculptor, the author of the Sigismund Chapel in Wawel Castle.";
        String corpusChristiAddress = "ul. Bozego Ciala 26";
        Double corpusChristiLat = 50.049601;
        Double corpusChristiLng = 19.944586;

        String pauliteName = "Paulite Church on the Rock";
        String pauliteDescription = "Centuries-old tradition has associated the Church on the Rock with the martyred Bishop Stanislaus. The Church dignitary suffered death in this place as a result of the conflict with King Bolesław the Brave in the eleventh century. Very soon, the person of the Bishop became the object of worship, and the place of his death - a pilgrimage destination. The present church dates to the mid- eighteenth century. The façade is decorated with a monumental staircase, under which is the entrance to the nineteenth century Honour Crypt. It is the place of burial for, e.g. Stanisław Wyspiański, composer Karol Szymanowski and mathematician Tadeusz Banachiewicz. The latest burial at the Church on the Rock – of the Nobel laureate Czesław Miłosz – gathered thousands of people.";
        String pauliteAddress = "ul. Skaleczna 15";
        Double pauliteLat = 50.048140;
        Double pauliteLng = 19.937664;

        Double swGertrudyLat = 50.056258;
        Double swGertrudyLng = 19.944772;
        Double wawelLat = 50.054222;
        Double wawelLng = 19.939532;
        Double stradomLat = 50.051900;
        Double stradomLng = 19.941174;
        Double placWolnicaLat = 50.048490;
        Double placWolnicaLng = 19.943148;
        Double koronaLat = 50.044007;
        Double koronaLng = 19.946771;
        Double smolkiLat = 50.041344;
        Double smolkiLng = 19.942858;
        Double pocztaLat = 50.059812;
        Double pocztaLng = 19.942115;
        Double starowislnaLat = 50.057023;
        Double starowislnaLng = 19.944518;
        Double miodowaLat = 50.053764;
        Double miodowaLng = 19.947941;
        Double wawrzyncaLat = 50.050937;
        Double wawrzyncaLng = 19.951009;
        Double bohaterowLat = 50.046762;
        Double bohaterowLng = 19.954689;


        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();
        realm.where(Place.class).findAll().clear();
        realm.where(Line.class).findAll().clear();
        realm.where(Stop.class).findAll().clear();

        Place alchemiaPlace = realm.createObject(Place.class);
        alchemiaPlace.setUUID("Sgwx");
        alchemiaPlace.setIsSeen(false);
        alchemiaPlace.setName(alchemiaName);
        alchemiaPlace.setCategory(context.getResources().getString(R.string.fun_category));
        alchemiaPlace.setImageResourceId(R.drawable.alchemia);
        alchemiaPlace.setBlockedImageRecourceId(R.drawable.alchemia1);
        alchemiaPlace.setCircleImageRecourceId(R.drawable.alchemia2);
        alchemiaPlace.setDescription(alchemiaDescription);
        alchemiaPlace.setAddress(alchemiaAddress);
        alchemiaPlace.setLatitude(alchemiaLat);
        alchemiaPlace.setLongitude(alchemiaLng);
        alchemiaPlace.setLetsPlayKrakow(false);

        Place kolankoPlace = realm.createObject(Place.class);
        kolankoPlace.setUUID(UUID.randomUUID().toString());
        alchemiaPlace.setIsSeen(false);
        kolankoPlace.setName(kolankoName);
        kolankoPlace.setCategory(context.getResources().getString(R.string.food_category));
        kolankoPlace.setImageResourceId(R.drawable.kolanko);
        kolankoPlace.setBlockedImageRecourceId(R.drawable.kolanko1);
        kolankoPlace.setCircleImageRecourceId(R.drawable.kolanko2);
        kolankoPlace.setDescription(kolankoDescription);
        kolankoPlace.setAddress(kolankoAddress);
        kolankoPlace.setLatitude(kolankoLat);
        kolankoPlace.setLongitude(kolankoLng);
        kolankoPlace.setLetsPlayKrakow(false);

        Place staraSynagogaPlace = realm.createObject(Place.class);
        staraSynagogaPlace.setUUID("cIKQ");
        alchemiaPlace.setIsSeen(false);
        staraSynagogaPlace.setName(staraSynagogaName);
        staraSynagogaPlace.setCategory(context.getResources().getString(R.string.monument_category));
        staraSynagogaPlace.setImageResourceId(R.drawable.synagoga);
        staraSynagogaPlace.setBlockedImageRecourceId(R.drawable.synagoga1);
        staraSynagogaPlace.setCircleImageRecourceId(R.drawable.synagoga2);
        staraSynagogaPlace.setDescription(staraSynagogaDescription);
        staraSynagogaPlace.setAddress(staraSynagogaAddress);
        staraSynagogaPlace.setLatitude(staraSynagogaLat);
        staraSynagogaPlace.setLongitude(staraSynagogaLng);
        staraSynagogaPlace.setLetsPlayKrakow(true);

        Place pierozkiPlace = realm.createObject(Place.class);
        pierozkiPlace.setUUID(UUID.randomUUID().toString());
        pierozkiPlace.setName(pierozkiName);
        alchemiaPlace.setIsSeen(false);
        pierozkiPlace.setCategory(context.getResources().getString(R.string.food_category));
        pierozkiPlace.setImageResourceId(R.drawable.vincent);
        pierozkiPlace.setBlockedImageRecourceId(R.drawable.vincent1);
        pierozkiPlace.setCircleImageRecourceId(R.drawable.vincent2);
        pierozkiPlace.setDescription(pierozkiDescription);
        pierozkiPlace.setAddress(pierozkiAddress);
        pierozkiPlace.setLatitude(pierozkiLat);
        pierozkiPlace.setLongitude(pierozkiLng);
        pierozkiPlace.setLetsPlayKrakow(false);

        Place singerPlace = realm.createObject(Place.class);
        singerPlace.setUUID(UUID.randomUUID().toString());
        singerPlace.setName(singerName);
        alchemiaPlace.setIsSeen(false);
        singerPlace.setCategory(context.getResources().getString(R.string.fun_category));
        singerPlace.setImageResourceId(R.drawable.singer);
        singerPlace.setBlockedImageRecourceId(R.drawable.singer1);
        singerPlace.setCircleImageRecourceId(R.drawable.singer2);
        singerPlace.setDescription(singerDescription);
        singerPlace.setAddress(singerAddress);
        singerPlace.setLatitude(singerLat);
        singerPlace.setLongitude(singerLng);
        singerPlace.setLetsPlayKrakow(false);

        Place paulitePlace = realm.createObject(Place.class);
        paulitePlace.setUUID(UUID.randomUUID().toString());
        paulitePlace.setName(pauliteName);
        alchemiaPlace.setIsSeen(false);
        paulitePlace.setCategory(context.getResources().getString(R.string.monument_category));
        paulitePlace.setImageResourceId(R.drawable.paulini);
        paulitePlace.setBlockedImageRecourceId(R.drawable.paulini1);
        paulitePlace.setCircleImageRecourceId(R.drawable.paulini2);
        paulitePlace.setDescription(pauliteDescription);
        paulitePlace.setAddress(pauliteAddress);
        paulitePlace.setLatitude(pauliteLat);
        paulitePlace.setLongitude(pauliteLng);
        paulitePlace.setLetsPlayKrakow(true);

        Place flowerPlace = realm.createObject(Place.class);
        flowerPlace.setUUID(UUID.randomUUID().toString());
        flowerPlace.setName(flowerPowerName);
        alchemiaPlace.setIsSeen(false);
        flowerPlace.setCategory(context.getResources().getString(R.string.fun_category));
        flowerPlace.setImageResourceId(R.drawable.flower);
        flowerPlace.setBlockedImageRecourceId(R.drawable.flower1);
        flowerPlace.setCircleImageRecourceId(R.drawable.flower2);
        flowerPlace.setDescription(flowerPowerDescription);
        flowerPlace.setAddress(flowerPowerAddress);
        flowerPlace.setLatitude(flowerPowerLat);
        flowerPlace.setLongitude(flowerPowerLng);
        flowerPlace.setLetsPlayKrakow(false);

        Place omertaPlace = realm.createObject(Place.class);
        omertaPlace.setUUID(UUID.randomUUID().toString());
        omertaPlace.setName(omertaName);
        alchemiaPlace.setIsSeen(false);
        omertaPlace.setCategory(context.getResources().getString(R.string.fun_category));
        omertaPlace.setImageResourceId(R.drawable.godfather);
        omertaPlace.setBlockedImageRecourceId(R.drawable.godfather1);
        omertaPlace.setCircleImageRecourceId(R.drawable.godfather2);
        omertaPlace.setDescription(omertaDescription);
        omertaPlace.setAddress(omertaAddress);
        omertaPlace.setLatitude(omertaLat);
        omertaPlace.setLongitude(omertaLng);
        omertaPlace.setLetsPlayKrakow(false);

        Place corpusPlace = realm.createObject(Place.class);
        corpusPlace.setUUID(UUID.randomUUID().toString());
        corpusPlace.setName(corpusChristiName);
        alchemiaPlace.setIsSeen(false);
        corpusPlace.setCategory(context.getResources().getString(R.string.monument_category));
        corpusPlace.setImageResourceId(R.drawable.bozegociala);
        corpusPlace.setBlockedImageRecourceId(R.drawable.bozegociala1);
        corpusPlace.setCircleImageRecourceId(R.drawable.bozegociala2);
        corpusPlace.setDescription(corpusChristiDescription);
        corpusPlace.setAddress(corpusChristiAddress);
        corpusPlace.setLatitude(corpusChristiLat);
        corpusPlace.setLongitude(corpusChristiLng);
        corpusPlace.setLetsPlayKrakow(true);

        Place sasiedziPlace = realm.createObject(Place.class);
        sasiedziPlace.setUUID(UUID.randomUUID().toString());
        sasiedziPlace.setName(sasiedziName);
        alchemiaPlace.setIsSeen(false);
        sasiedziPlace.setCategory(context.getResources().getString(R.string.food_category));
        sasiedziPlace.setImageResourceId(R.drawable.sasiedzi);
        sasiedziPlace.setBlockedImageRecourceId(R.drawable.sasiedzi1);
        sasiedziPlace.setCircleImageRecourceId(R.drawable.sasiedzi2);
        sasiedziPlace.setDescription(sasiedziDescription);
        sasiedziPlace.setAddress(sasiedziAddress);
        sasiedziPlace.setLatitude(sasiedziLat);
        sasiedziPlace.setLongitude(sasiedziLng);
        sasiedziPlace.setLetsPlayKrakow(false);

        Place remuhPlace = realm.createObject(Place.class);
        remuhPlace.setUUID(UUID.randomUUID().toString());
        remuhPlace.setName(remuhName);
        alchemiaPlace.setIsSeen(false);
        remuhPlace.setCategory(context.getResources().getString(R.string.monument_category));
        remuhPlace.setImageResourceId(R.drawable.remuh);
        remuhPlace.setBlockedImageRecourceId(R.drawable.remuh1);
        remuhPlace.setCircleImageRecourceId(R.drawable.remuh2);
        remuhPlace.setDescription(remuhDescription);
        remuhPlace.setAddress(remuhAddress);
        remuhPlace.setLatitude(remuhLat);
        remuhPlace.setLongitude(remuhLng);
        remuhPlace.setLetsPlayKrakow(true);

        Place isaacPlace = realm.createObject(Place.class);
        isaacPlace.setUUID(UUID.randomUUID().toString());
        isaacPlace.setName(isaacName);
        alchemiaPlace.setIsSeen(false);
        isaacPlace.setCategory(context.getResources().getString(R.string.monument_category));
        isaacPlace.setImageResourceId(R.drawable.isaac);
        isaacPlace.setBlockedImageRecourceId(R.drawable.isaac1);
        isaacPlace.setCircleImageRecourceId(R.drawable.isaac2);
        isaacPlace.setDescription(isaacDescription);
        isaacPlace.setAddress(isaacAddress);
        isaacPlace.setLatitude(isaacLat);
        isaacPlace.setLongitude(isaacLng);
        isaacPlace.setLetsPlayKrakow(true);

        Line firstLine = realm.createObject(Line.class);
        firstLine.setName(8);
        RealmList<Stop> firstStops = new RealmList<>();

        Stop swGertrudyStop = realm.createObject(Stop.class);
        swGertrudyStop.setUuid(UUID.randomUUID().toString());
        swGertrudyStop.setName("Sw. Gertrudy");
        swGertrudyStop.setLatitude(swGertrudyLat);
        swGertrudyStop.setLongitude(swGertrudyLng);
        Stop wawelStop = realm.createObject(Stop.class);
        wawelStop.setUuid(UUID.randomUUID().toString());

        wawelStop.setName("Wawel");
        wawelStop.setLatitude(wawelLat);
        wawelStop.setLongitude(wawelLng);
        Stop stradomStop = realm.createObject(Stop.class);
        stradomStop.setUuid(UUID.randomUUID().toString());

        stradomStop.setName("Stradom");
        stradomStop.setLatitude(stradomLat);
        stradomStop.setLongitude(stradomLng);
        Stop wolnicaStop = realm.createObject(Stop.class);
        wolnicaStop.setUuid(UUID.randomUUID().toString());

        wolnicaStop.setName("Plac Wolnica");
        wolnicaStop.setLatitude(placWolnicaLat);
        wolnicaStop.setLongitude(placWolnicaLng);

        Stop koronaStop = realm.createObject(Stop.class);
        koronaStop.setUuid(UUID.randomUUID().toString());

        koronaStop.setName("Korona");
        koronaStop.setLatitude(koronaLat);
        koronaStop.setLongitude(koronaLng);

        Stop smolkiStop = realm.createObject(Stop.class);
        smolkiStop.setUuid(UUID.randomUUID().toString());
        smolkiStop.setName("Smolki");
        smolkiStop.setLatitude(smolkiLat);
        smolkiStop.setLongitude(smolkiLng);
        realm.commitTransaction();
        realm.beginTransaction();
        firstStops.add(swGertrudyStop);
        firstStops.add(wawelStop);
        firstStops.add(stradomStop);
        firstStops.add(wolnicaStop);
        firstStops.add(koronaStop);
        firstStops.add(smolkiStop);
        firstLine.setStops(firstStops);
        realm.commitTransaction();

        realm.beginTransaction();
        Line secondLine = realm.createObject(Line.class);
        secondLine.setName(24);
        RealmList<Stop> secondStops = new RealmList<>();

        Stop pocztaStop = realm.createObject(Stop.class);
        pocztaStop.setUuid(UUID.randomUUID().toString());
        pocztaStop.setName("Poczta Glowna");
        pocztaStop.setLatitude(pocztaLat);
        pocztaStop.setLongitude(pocztaLng);

        Stop starowislnaStop = realm.createObject(Stop.class);
        starowislnaStop.setUuid(UUID.randomUUID().toString());
        starowislnaStop.setName("Starowislna");
        starowislnaStop.setLatitude(starowislnaLat);
        starowislnaStop.setLongitude(starowislnaLng);

        Stop miodowaStop = realm.createObject(Stop.class);
        miodowaStop.setUuid(UUID.randomUUID().toString());
        miodowaStop.setName("Miodowa");
        miodowaStop.setLatitude(miodowaLat);
        miodowaStop.setLongitude(miodowaLng);

        Stop wawrzyncaStop = realm.createObject(Stop.class);
        wawrzyncaStop.setUuid(UUID.randomUUID().toString());
        wawrzyncaStop.setName("Sw. Wawrzynca");
        wawrzyncaStop.setLatitude(wawrzyncaLat);
        wawrzyncaStop.setLongitude(wawrzyncaLng);

        Stop bohaterowStop = realm.createObject(Stop.class);
        bohaterowStop.setUuid(UUID.randomUUID().toString());
        bohaterowStop.setName("Plac bohaterow getta");
        bohaterowStop.setLatitude(bohaterowLat);
        bohaterowStop.setLongitude(bohaterowLng);

        realm.commitTransaction();
        realm.beginTransaction();
        secondStops.add(pocztaStop);
        secondStops.add(starowislnaStop);
        secondStops.add(miodowaStop);
        secondStops.add(wawrzyncaStop);
        secondStops.add(bohaterowStop);
        secondLine.setStops(secondStops);
        realm.commitTransaction();
    }
}
