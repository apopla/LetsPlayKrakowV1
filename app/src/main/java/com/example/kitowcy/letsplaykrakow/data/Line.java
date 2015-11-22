package com.example.kitowcy.letsplaykrakow.data;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Paulina on 2015-11-22.
 */
public class Line extends RealmObject{
    @PrimaryKey
    private Integer name;
    private RealmList<Stop> stops;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }


    public RealmList<Stop> getStops() {
        return stops;
    }

    public void setStops(RealmList<Stop> stops) {
        this.stops = stops;
    }
}
