package com.example.sportshci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;

import RoomDatabase.MyLocalDatabase;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static MyLocalDatabase myLocalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        myLocalDatabase = Room.databaseBuilder(getApplicationContext(), MyLocalDatabase.class, "localDB").allowMainThreadQueries().build();

        if (findViewById(R.id.fragment_container != null)) {
            if (savedInstanceState!=null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new RoomUI_Fragment()).commit();
        }
    }
}