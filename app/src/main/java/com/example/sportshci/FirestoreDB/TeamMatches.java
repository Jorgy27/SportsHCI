package com.example.sportshci.FirestoreDB;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

@IgnoreExtraProperties
public class TeamMatches {
    private int code;
    private String city;
    private String country;
    private String sport;
    private Date date;
    private String gender;
    private List<String> teams;
    private List<Integer> scores;



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

    public List<String> getTeams() {
        return teams;
    }

    public void setTeams(List<String> teams) {
        this.teams = teams;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
