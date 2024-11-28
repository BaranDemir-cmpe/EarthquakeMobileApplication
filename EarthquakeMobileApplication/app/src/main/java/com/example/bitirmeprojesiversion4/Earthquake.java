package com.example.bitirmeprojesiversion4;

public class Earthquake {
    private String date;
    private String magnitude;
    private String location;


    public Earthquake(String date, String magnitude, String location) {
        this.date = date;
        this.magnitude = magnitude;
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
