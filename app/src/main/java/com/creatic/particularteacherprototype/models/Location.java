package com.creatic.particularteacherprototype.models;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class Location {
    long id;
    long offerId;
    float latitude;
    float longitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long user_id) {
        this.offerId = user_id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
