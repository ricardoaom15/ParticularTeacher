package com.creatic.particularteacherprototype.models;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class Rating {
    long id;
    float rating;
    String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
