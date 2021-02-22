package com.example.triptime.TripValues;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Query("SELECT * FROM Trip WHERE id = :tripId")
    Trip getTrip(int tripId);

    @Query("SELECT * FROM trip")
    List<Trip> getAllTrips();

    @Update
    void update(Trip trip);

    @Insert
    void insertAll(Trip... trips);

    @Query("SELECT * FROM Trip WHERE user_id = :userId")
    List<Trip> getUserTrips(int userId);
}
