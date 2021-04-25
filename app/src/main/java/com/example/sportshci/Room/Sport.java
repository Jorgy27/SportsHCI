package com.example.sportshci.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sports")
public class Sport {
    @PrimaryKey(autoGenerate = true)
    private int code;
    private String name;
    private String type;
    private String gender;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
