package com.example.triptime.TripValues;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "trip_destination")
    private String tripDestination;

    @ColumnInfo(name = "trip_name")
    private String tripName;

    @ColumnInfo(name = "rating_bar")
    private float ratingBar;

    @ColumnInfo(name = "favorite_status")
    private boolean favStatus;

    @ColumnInfo(name = "user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Trip(String tripDestination, String tripName, float ratingBar, boolean favStatus, int userId) {
        this.tripDestination = tripDestination;
        this.tripName = tripName;
        this.ratingBar = ratingBar;
        this.favStatus = favStatus;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public String getTripName() {
        return tripName;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public boolean isFavStatus() {
        return favStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public void setFavStatus(boolean favStatus) {
        this.favStatus = favStatus;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", tripDestination='" + tripDestination + '\'' +
                ", tripName='" + tripName + '\'' +
                ", ratingBar=" + ratingBar +
                ", favStatus=" + favStatus +
                '}';
    }
}
