package com.example.sportshci.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    //---------------Sport methods---------------
    @Insert
    public void addSport(Sport sport);

    @Query("SELECT * FROM SPORTS")
    public List<Sport> getSports();

    @Query("SELECT SPORTS.CODE FROM SPORTS WHERE SPORTS.NAME = :sportName")
    public int getSportIdByName(String sportName);

    @Query("SELECT SPORTS.NAME FROM SPORTS WHERE SPORTS.CODE = :sportCode")
    public String getSportNameById(int sportCode);

    @Query("SELECT COUNT(*) FROM SPORTS")
    public int countSports();

    @Delete
    public  void deleteSport(Sport sport);
    
    //---------------Athlete methods---------------
    @Insert
    public void addAthlete(Athlete athlete);

    @Query("SELECT * FROM ATHLETES")
    public List<Athlete> getAthletes();

    @Delete
    public void deleteAthlete(Athlete athlete);

    //---------------Team methods---------------
    @Insert
    public void addTeam(Team team);

    @Query("SELECT * FROM Teams")
    public List<Team> getTeams();

    @Delete
    public void deleteTeam(Team team);
}
