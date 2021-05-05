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
    private String gender;
    private Map<String,String> athletes;
    private Map<String,String> scores;


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

    public Map<String, String> getAthletes() {
        return athletes;
    }

    public void setAthletes(Map<String, String> athletes) {
        this.athletes = athletes;
    }

    public Map<String, String> getScores() {
        return scores;
    }

    public void setScores(Map<String, String> scores) {
        this.scores = scores;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
