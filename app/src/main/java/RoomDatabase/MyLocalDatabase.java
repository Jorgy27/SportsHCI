package RoomDatabase;

import androidx.room.Database;

import RoomDatabase.MyDao;
import RoomDatabase.Sport;

@Database(entities = {Sport.class},version = 1)
public abstract class MyLocalDatabase {
    public abstract MyDao myDao();
}
