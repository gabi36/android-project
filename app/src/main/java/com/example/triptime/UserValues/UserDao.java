package com.example.triptime.UserValues;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE mail = :userMail")
    User getUserMail(String userMail);

    @Query("SELECT * FROM User WHERE mail = :userMail and password = :userPassword")
    User getUserData(String userMail, String userPassword);

    @Insert
    void insert(User user);
}
