package com.example.triptime.UserValues;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String mail;
    private String password;

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
