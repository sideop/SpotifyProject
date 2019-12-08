package com.example.spotifyproject;

public class User {

    public String birthdate;
    public String country;
    public String display_name;
    public String email;
    public String id;

    public User(String name, String id) {
        display_name = name;
        this.id = id;
    }


}
