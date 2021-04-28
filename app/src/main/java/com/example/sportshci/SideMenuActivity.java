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

import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Sports.AddSport;
import com.example.sportshci.Sports.*;
import com.example.sportshci.Sports.SportsRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SideMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SportsRecyclerAdapter.OnSportListener {

    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    private DrawerLayout drawer;
    List<Sport> sportList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);

        fragmentManager = getSupportFragmentManager();
        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "sportsDB").allowMainThreadQueries().build(); //build database for this activity

        //Gets the string of the intent that shows what the user wants to see. (Sports,Matches,Athletes/Teams)
        Bundle extras = getIntent().getExtras();
        String action = extras.getString("sideMenu");

        initialiseSideMenu();

        switch (action) {
            case "Sports":
                sportList = new ArrayList<>();
                recyclerView = findViewById(R.id.recyclerList);

                setSportsInfo();
                setSportsAdapter();

                //Toast.makeText(this,"Sports",Toast.LENGTH_LONG).show();

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
        //Set an Item Selected Listener on the items of the side menu
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    private void setSportsAdapter() {
        SportsRecyclerAdapter adapter = new SportsRecyclerAdapter(sportList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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

        switch (item.getItemId()) {
            case R.id.nav_add:
                fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddSport()).commit();
                break;
            case R.id.nav_remove:

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSportClick(int position) {

        String sport = sportList.get(position).getName();
        //New activity here
        Toast.makeText(this,sport,Toast.LENGTH_LONG).show();
    }
}