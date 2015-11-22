package com.example.kitowcy.letsplaykrakow.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Paulina on 2015-11-22.
 */
public class Stop extends RealmObject {
    @PrimaryKey
    private String uuid;
    private String name;
    private Double latitude;
    private Double longitude;

    public Stop() {
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {

        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
