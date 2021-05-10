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

    @Query("SELECT * FROM SPORTS ORDER BY SPORTS.type DESC")
    public List<Sport> getSports();

    @Query("SELECT SPORTS.CODE FROM SPORTS WHERE SPORTS.NAME = :sportName")
    public int getSportIdByName(String sportName);

    @Query("SELECT SPORTS.NAME FROM SPORTS WHERE SPORTS.CODE = :sportCode ")
    public String getSportNameById(int sportCode);


    @Delete
    public  void deleteSport(Sport sport);
    
    //---------------Athlete methods---------------
    @Insert
    public void addAthlete(Athlete athlete);

    @Query("SELECT * FROM ATHLETES")
    public List<Athlete> getAthletes();

    @Query("SELECT * FROM ATHLETES WHERE ATHLETES.SPORT = :sportCode")
    public List<Athlete> getAthletesBySport(int sportCode);

    @Delete
    public void deleteAthlete(Athlete athlete);

    @Query("SELECT COUNT(ATHLETES.code) FROM ATHLETES WHERE ATHLETES.SPORT = :sportCode")
    public int countAthletesOfSport(int sportCode);

    //---------------Team methods---------------
    @Insert
    public void addTeam(Team team);

    @Query("SELECT * FROM Teams GROUP BY TEAMS.name")
    public List<Team> getTeams();

    @Query("SELECT * FROM TEAMS WHERE TEAMS.SPORT = :sportCode")
    public List<Team> getTeamsBySport(int sportCode);

    @Delete
    public void deleteTeam(Team team);
}
