package com.example.kitowcy.letsplaykrakow.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lukasz on 21.11.15.
 */
public class Place extends RealmObject {

    @PrimaryKey
    private String UUID;
    private double latitude;
    private double longitude;
    private String name;
    private String category;
    private String description;
    private String address;
    private int imageResourceId;
    private boolean letsPlayKrakow;
    private String beaconName;
    private boolean isSeen;
    private boolean isBlocked;
    private int blockedImageRecourceId;
    private int circleImageRecourceId;


    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public Place() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public boolean isLetsPlayKrakow() {
        return letsPlayKrakow;
    }

    public void setLetsPlayKrakow(boolean letsPlayKrakow) {
        this.letsPlayKrakow = letsPlayKrakow;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getBlockedImageRecourceId() {
        return blockedImageRecourceId;
    }

    public void setBlockedImageRecourceId(int blockedImageRecourceId) {
        this.blockedImageRecourceId = blockedImageRecourceId;
    }

    public int getCircleImageRecourceId() {
        return circleImageRecourceId;
    }

    public void setCircleImageRecourceId(int circleImageRecourceId) {
        this.circleImageRecourceId = circleImageRecourceId;
    }
}
