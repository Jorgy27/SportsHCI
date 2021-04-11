package RoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void insertSport(Sport sport);

    @Delete
    void deleteSport(Sport sport);

    @Query("Select * From sports")
    List<Sport> getAll();
}
