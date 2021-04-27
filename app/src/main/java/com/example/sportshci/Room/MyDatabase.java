package com.example.sportshci.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sport.class, Athlete.class, Team.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
