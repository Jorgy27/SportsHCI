package com.example.sportshci.FirestoreDB;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SingleMatches {
    private int code;
    private String city;
    private String country;
    private Date date;
    private String sport;
    private Map athletes;


    public SingleMatches(){};


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Map getAthletes() {
        return athletes;
    }

    public void setAthletes(Map athletes) {
        this.athletes = athletes;
    }


}
