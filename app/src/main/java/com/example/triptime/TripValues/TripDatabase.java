package com.example.triptime.TripValues;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripDatabase extends RoomDatabase {

    public abstract TripDao tripDao();
}
