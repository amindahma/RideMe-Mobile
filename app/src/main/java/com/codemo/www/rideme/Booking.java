package com.codemo.www.rideme;

/**
 * Created by aminda on 9/17/2018.
 */

public class Booking {
    private String date;
    private String pack;
    private String rent;

    public Booking(String date, String pack, String rent) {
        this.date = date;
        this.pack = pack;
        this.rent = rent;
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
}
