package com.example.sportshci.FirestoreDB;

import java.util.Date;
import java.util.Map;

public class TeamMatches {
    private int code;
    private String city;
    private String country;
    private String sport;
    private Date date;
    private Map teams;

    public TeamMatches(){};

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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map getTeams() {
        return teams;
    }

    public void setTeams(Map teams) {
        this.teams = teams;
    }
}
