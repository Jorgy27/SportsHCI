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
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sportshci.AthletesAndTeams.AthletesAndTeams;
import com.example.sportshci.Matches.*;
import com.example.sportshci.Sports.*;
import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Sports.AddSport;
import com.example.sportshci.Sports.*;
import com.example.sportshci.Sports.SportsRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SideMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SportsRecyclerAdapter.OnSportListener, RemoveSportsAdapter.OnSportListener {
    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    public static FirebaseFirestore db;
    private DrawerLayout drawer;
    private List<Sport> sportList;
    private RecyclerView recyclerView;
    public static String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        fragmentManager = getSupportFragmentManager();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "sportsDB").allowMainThreadQueries().build(); //build database for this activity
        db = FirebaseFirestore.getInstance();

        //Gets the string of the intent that shows what the user wants to see. (Sports,Matches,Athletes/Teams)
        Bundle extras = getIntent().getExtras();
        action = extras.getString("sideMenu");

        initialiseSideMenu();

        switch (action) {
            case "Sports":
                sportList = new ArrayList<>();
                recyclerView = findViewById(R.id.recyclerList);

                setSportsInfo();
                SportsRecyclerAdapter adapter = new SportsRecyclerAdapter(sportList, this);
                setRecyclerLayout();
                recyclerView.setAdapter(adapter);
                break;
            case "Athletes":
                Toast.makeText(this,"Athletes",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void initialiseSideMenu() {
        //Sets the toolbar to act like an action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialise the drawer layout we have created and add the ActionBarDrawerToggle so we can use the Drawer as an ActionBar
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setVisibility(View.VISIBLE);
    }

    private void HideSideMenu()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setVisibility(View.INVISIBLE);
    }


    private  void setRecyclerLayout(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setSportsInfo() {
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

        HideSideMenu();
        switch (action)
        {
            case "Sports":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddSport()).commit();
                        break;
                    case R.id.nav_remove:
                        RemoveSportsAdapter adapter = new RemoveSportsAdapter(sportList, this);
                        setRecyclerLayout();
                        recyclerView.setAdapter(adapter);
                        break;
                }
             case "Athletes":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AthletesAndTeams()).commit();
                            break;
                     case R.id.nav_remove:

                        break;
                 }
                        break;
             case"Matches":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddMatch()).commit();
                        break;
                    case R.id.nav_remove:

                        break;
                }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSportClick(int position) {
        String typeOfSport = sportList.get(position).getType();
        action="Matches"; //TODO na to ksanakanw Sports
        fragmentManager.beginTransaction().replace(R.id.fragment_container, new MatchesViewFragment()).commit();
        if(typeOfSport.equals("Team")){
            //fragmentManager.beginTransaction().replace(R.id.fragment_container, new TeamMatches()).commit();
        }else{
            //an einai single na tou vgazei kai ena option me ton arithmo contestands
            Toast.makeText(this,typeOfSport,Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onRemoveSportClick(int position) {
        Sport sport = sportList.get(position);
        myDatabase.myDao().deleteSport(sport);
    }
}