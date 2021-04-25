package com.example.sportshci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;


import android.os.Bundle;

import com.example.sportshci.Room.MyDatabase;


public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        myDatabase = Room.databaseBuilder(getApplicationContext(),MyDatabase.class, "sportsDB").allowMainThreadQueries().build(); //build database for this activity

        if (findViewById(R.id.fragment_container)!=null) {
            if (savedInstanceState != null) {
                return;
            }
        }
        HomeFragment homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment).commit();
    }
}