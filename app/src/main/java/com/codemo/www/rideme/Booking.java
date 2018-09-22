package com.codemo.www.rideme;

/**
 * Created by aminda on 9/17/2018.
 */

public class Booking {
    private String date;
    private String pack;
    private String rent;
    private String type;
    private String hours;

    public Booking(String date, String pack, String rent, String type, String hours) {
        this.date = date;
        this.pack = pack;
        this.rent = rent;
        this.setType(type);
        this.setHours(hours);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
