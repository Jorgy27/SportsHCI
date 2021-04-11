package RoomDatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "athletes",
        foreignKeys = {@ForeignKey(entity = Sport.class,
                        parentColumns = "code",
                        childColumns = "sport",
                        onDelete = ForeignKey.CASCADE)
                        }
        )
public class Athlete {
    @PrimaryKey(autoGenerate = true)
    private int code;
    private String fName;
    private String lName;
    private String city;
    private String country;
    private int sport;
    private String birthday;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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
