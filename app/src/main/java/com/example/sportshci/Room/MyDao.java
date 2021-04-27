package com.example.sportshci.Room;

import androidx.room.Dao;
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

    @Query("SELECT COUNT(*) FROM SPORTS")
    public int countSports();

    //---------------Athlete methods---------------
    @Insert
    public void addAthlete(Athlete athlete);

    @Query("SELECT * FROM ATHLETES")
    public List<Athlete> getAthletes();

    //---------------Team methods---------------
    @Insert
    public void addTeam(Team team);

    @Query("SELECT * FROM Teams")
    public List<Team> getTeams();
}
