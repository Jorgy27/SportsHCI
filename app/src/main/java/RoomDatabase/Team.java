package RoomDatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams",
        foreignKeys = {@ForeignKey(entity = Sport.class,
                        parentColumns = "code",
                        childColumns = "sport",
                        onDelete = ForeignKey.CASCADE)
                        }
        )
public class Team {
    @PrimaryKey(autoGenerate = true)
    private int code;
    private String name;
    private String field;
    private String country;
    private int sport;
    private String birthday;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
