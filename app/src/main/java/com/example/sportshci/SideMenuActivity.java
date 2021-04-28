package com.example.sportshci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sportshci.Athletes.AthletesAndTeams;
import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Sports.AddSport;
import com.example.sportshci.Sports.*;
import com.example.sportshci.Sports.SportsRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SideMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    private DrawerLayout drawer;
    List<Sport> sportList;
    private RecyclerView recyclerView;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        fragmentManager = getSupportFragmentManager();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "sportsDB").allowMainThreadQueries().build(); //build database for this activity

        Bundle extras = getIntent().getExtras();
        action = extras.getString("sideMenu");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switch (action){
            case "Sports":
                sportList = new ArrayList<>();
                recyclerView = findViewById(R.id.recyclerList);

                setSportsInfo();
                setSportsAdapter();

                Toast.makeText(this,"Sports",Toast.LENGTH_LONG).show();
                break;
            case "Athletes":
                Toast.makeText(this,"Athletes",Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void setSportsAdapter() {
        SportsRecyclerAdapter adapter = new SportsRecyclerAdapter(sportList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setSportsInfo(){
        sportList = MainActivity.myDatabase.myDao().getSports();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (action)
        {
            case "Sports":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddSport()).commit();
                        break;
                    case R.id.nav_remove:

                        break;
                }
                break;
            case "Athletes":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AthletesAndTeams()).commit();
                        break;
                    case R.id.nav_remove:

                        break;
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}