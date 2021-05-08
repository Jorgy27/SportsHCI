package com.example.sportshci;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportshci.AthletesAndTeams.AthleteTableAdapter;
import com.example.sportshci.AthletesAndTeams.AthletesAndTeams;
import com.example.sportshci.AthletesAndTeams.RemoveAthletesAdapter;
import com.example.sportshci.AthletesAndTeams.RemoveTeamAdapter;
import com.example.sportshci.AthletesAndTeams.TeamTableAdapter;
import com.example.sportshci.FirestoreDB.SingleMatches;
import com.example.sportshci.FirestoreDB.TeamMatches;
import com.example.sportshci.Matches.*;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Room.Team;
import com.example.sportshci.Sports.*;
import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Room.Team;
import com.example.sportshci.Sports.AddSport;
import com.example.sportshci.Sports.RemoveSportsAdapter;
import com.example.sportshci.Sports.SportsRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SideMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SportsRecyclerAdapter.OnSportListener, RemoveSportsAdapter.OnSportListener, AthleteTableAdapter.OnAthleteListener, TeamTableAdapter.OnTeamListener, RemoveAthletesAdapter.OnAthleteListener, RemoveTeamAdapter.OnTeamListener, RemoveTeamMatchAdapter.OnTeamMatchListener{
    public static FragmentManager fragmentManager;
    public static MyDatabase myDatabase;
    public static FirebaseFirestore db;
    DocumentReference documentReference;
    private DrawerLayout drawer;
    private List<Sport> sportList;
    private List<Athlete> athleteList;
    private List<Team> teamList;
    private List<TeamMatches> teamMatchesList;
    private List<SingleMatches> singleMatchesList;
    private RecyclerView recyclerView;
    public static String currentAction;
    public String action;
    public static Sport clickedSport;
    private Toolbar toolbar;
    static boolean onDelete = false;
    private static Integer matchPosition;
    private static ConcatAdapter removeMatchAdapter;
    private String document;
    private int matchAthleteSize = 0;

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

        recyclerView = findViewById(R.id.recyclerList);

        if(currentAction==null)
        {
            currentAction=action;
        }

        switch (currentAction) {
            case "Sports":
                OnCreateSports();
                break;
            case "Athletes":
                OnCreateAthletesAndSports();
                break;
            case "Matches":
                OnCreateMatches();
                break;
        }
    }

    private void initialiseSideMenu() {
        //Sets the toolbar to act like an action bar
        toolbar = findViewById(R.id.toolbar);
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

    public void HideSideMenu()
    {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setVisibility(View.INVISIBLE);
    }

    private void OnCreateSports()
    {
        currentAction="Sports";
        sportList = new ArrayList<>();
        setSportsInfo();

        if(onDelete)
        {
            SetSportRemove();
            return;
        }
        onDelete=false;

        SportsRecyclerAdapter adapter = new SportsRecyclerAdapter(sportList, this);
        setRecyclerLayout();
        recyclerView.setAdapter(adapter);
    }

    private void OnCreateAthletesAndSports()
    {
        currentAction="Athletes";
        athleteList = new ArrayList<>();
        teamList = new ArrayList<>();
        setAthletesInfo();
        setTeamsInfo();

        if(onDelete)
        {
            SetAthleteAndTeamRemove();
            return;
        }
        onDelete=false;

        AthleteTableAdapter athleteAdapter = new AthleteTableAdapter(athleteList,this);
        TeamTableAdapter teamAdapter = new TeamTableAdapter(teamList,this);
        setRecyclerLayout();

        ConcatAdapter concatAdapter = new ConcatAdapter(athleteAdapter,teamAdapter);
        recyclerView.setAdapter(concatAdapter);
    }



    private void OnCreateMatches()
    {
        currentAction="Matches";
        sportList = new ArrayList<>();
        setSportsInfo();
        if(onDelete)
        {
            createMatchesRemove();
            return;
        }
        onDelete=false;
        ViewMatchesFragmentCreate();
    }


    private  void setRecyclerLayout(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setSportsInfo() {
        sportList = MainActivity.myDatabase.myDao().getSports();
    }
    private void setAthletesInfo() {
        athleteList = MainActivity.myDatabase.myDao().getAthletes();
    }
    private void setTeamsInfo() {
        teamList = MainActivity.myDatabase.myDao().getTeams();
    }



    @Override
    public void onBackPressed() {
        onDelete=false;

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if(currentAction=="Matches")
        {
            HandleMatchesBackButton();
        }
        else
        {
            if(toolbar.getVisibility()==View.INVISIBLE) // otan einai INVISIBLE to toolbar den einai sto arxiko fragment tou activity ara otan patisei back pigenei ekei
            {
                RefreshActivity();
            }
            else
            {
                currentAction=null;
            }
        }

        super.onBackPressed();
    }

    public void HandleMatchesBackButton() {
        if (toolbar.getVisibility() == View.VISIBLE) // otan einai INVISIBLE to toolbar einai sto add matches
        {
            matchPosition = null;
            currentAction = "Sports";
        }
        RefreshActivity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        HideSideMenu();
        switch (currentAction)
        {
            case "Sports":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        onDelete=false;
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddSport()).commit();
                        break;
                    case R.id.nav_remove:
                        SetSportRemove();
                        break;
                }
                break;
            case "Athletes":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        onDelete=false;
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new AthletesAndTeams()).commit();
                        break;
                    case R.id.nav_remove:
                        SetAthleteAndTeamRemove();
                        break;
                }
                break;
            case"Matches":
                switch (item.getItemId()) {
                    case R.id.nav_add:
                        onDelete=false;
                        addMatchFragment();
                        break;
                    case R.id.nav_remove:
                        setMatchRemove();
                        break;
                }
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void addMatchFragment()
    {
        if(document.equals("TeamMatch"))
        {
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new AddTeamMatch()).commit();
        }else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Insert Number of Athletes");

            final EditText input = new EditText(this);

            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    matchAthleteSize = Integer.parseInt(input.getText().toString());

                    Bundle bundle =new Bundle();
                    bundle.putInt("numberOfAthletes",matchAthleteSize);
                    //set fragmentclass arguments
                    AddSingleMatch singleMatchFragment = new AddSingleMatch();
                    singleMatchFragment.setArguments(bundle);

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, singleMatchFragment).commit();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    RefreshActivity();
                }
            });

            builder.show();
        }

    }

    private void setMatchRemove() {
        onDelete = true;
        String typeOfClickedSport = clickedSport.getType();
        if(typeOfClickedSport.equals("Team"))
        {
            db.collection("Matches")
                    .document("TeamMatch")
                    .collection("T_Matches")
                    .whereEqualTo("sport",clickedSport.getName())
                    .whereEqualTo("gender",clickedSport.getGender())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task)
                        {
                            if(task.isSuccessful())
                            {
                                TeamMatches teamMatch = new TeamMatches();
                                teamMatchesList = new ArrayList<TeamMatches>();

                                for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                                {
                                    teamMatch = documentSnapshot.toObject(TeamMatches.class);
                                    teamMatchesList.add(teamMatch);
                                }

                            }
                        }
                    });
            RemoveTeamMatchAdapter removeTeamMatchAdapter = new RemoveTeamMatchAdapter(teamMatchesList,this);
            removeMatchAdapter = new ConcatAdapter(removeTeamMatchAdapter);
            createMatchesRemove();

        }else if (typeOfClickedSport.equals("Single")){

        }

    }

    //TODO na allaksw onoma
    private void createMatchesRemove()
    {
        HideSideMenu();
        setRecyclerLayout();
        recyclerView.setAdapter(removeMatchAdapter);
    }

    private void SetSportRemove()
    {
        onDelete = true;
        HideSideMenu();
        RemoveSportsAdapter adapter = new RemoveSportsAdapter(sportList, this);
        setRecyclerLayout();
        recyclerView.setAdapter(adapter);
    }

    private void SetAthleteAndTeamRemove() {
        onDelete = true;
        HideSideMenu();

        RemoveAthletesAdapter adapterAthletes = new RemoveAthletesAdapter(athleteList, this);
        RemoveTeamAdapter adapterTeams = new RemoveTeamAdapter(teamList, this);
        ConcatAdapter adapter = new ConcatAdapter(adapterAthletes, adapterTeams);

        setRecyclerLayout();
        recyclerView.setAdapter(adapter);

    }
    private void handleMatches(String sport, String gender, String document, String collection) {

        db.collection("Matches")
                .document(document)
                .collection(collection)
                .whereEqualTo("sport",sport)
                .whereEqualTo("gender",gender)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task)
                    {
                        if(task.isSuccessful())
                        {
                            if(document.equals("TeamMatch"))
                            {
                                TeamMatches teamMatch = new TeamMatches();
                                teamMatchesList = new ArrayList<TeamMatches>();

                                for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                                {

                                    teamMatch = documentSnapshot.toObject(TeamMatches.class);
                                    teamMatchesList.add(teamMatch);
                                }

                                TeamMatchesAdapter adapter = new TeamMatchesAdapter(teamMatchesList);
                                setRecyclerLayout();
                                recyclerView.setAdapter(adapter);

                            }else if(document.equals("SingleMatch"))
                            {
                                SingleMatches singleMatch = new SingleMatches();
                                singleMatchesList = new ArrayList<SingleMatches>();

                                for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                                {

                                    singleMatch = documentSnapshot.toObject(SingleMatches.class);
                                    singleMatchesList.add(singleMatch);

                                    Log.d("FIREBASE",documentSnapshot.getId()+" => "+documentSnapshot.getData());
                                }

                                //change to SingleMatchesAdapter
                                SingleMatchesAdapter adapter = new SingleMatchesAdapter(singleMatchesList);
                                setRecyclerLayout();
                                recyclerView.setAdapter(adapter);
                            }

                        } else {
                            Log.d("ERROR","Error getting documents: ", task.getException());
                        }


                    }
                });
    }


    @Override
    public void onSportClick(int position) {
        matchPosition = position;
        ViewMatchesFragmentCreate();
    }
    public void ViewMatchesFragmentCreate()
    {
        currentAction = "Matches";
        String sport = sportList.get(matchPosition).getName();
        String typeOfSport = sportList.get(matchPosition).getType();
        String genderOfSport = sportList.get(matchPosition).getGender();

        //Save the information about the clicked sport on a static array so i can use it when the user adds a match on the sport he clicked
        clickedSport = new Sport();
        clickedSport.setCode(0);
        clickedSport.setName(sport);
        clickedSport.setGender(genderOfSport);
        clickedSport.setType(typeOfSport);

        String collection;

        //fragmentManager.beginTransaction().replace(R.id.fragment_container, new MatchesViewFragment()).commit();
        switch (typeOfSport){
            case "Team":
                document= "TeamMatch";
                collection = "T_Matches";
                handleMatches(sport,genderOfSport,document,collection);
                break;
            case "Single":
                document = "SingleMatch";
                collection = "S_Matches";
                handleMatches(sport,genderOfSport,document,collection);
                break;
        }
    }

    @Override
    public void onRemoveTeamListener(int position) {
        Team team = teamList.get(position);
        myDatabase.myDao().deleteTeam(team);
        RefreshActivity();
    }

    @Override
    public void onRemoveAthleteClick(int position){
        Athlete athlete = athleteList.get(position);
        myDatabase.myDao().deleteAthlete(athlete);
        RefreshActivity();
    }

    @Override
    public void onRemoveSportClick(int position) {
        Sport sport = sportList.get(position);
        myDatabase.myDao().deleteSport(sport);
        RefreshActivity();
    }


    public void RefreshActivity()
    {
        //Kanei restart to activity gia na ksanapaei sti lista me ta athlimata h me tis omades kai tous athlites
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onAthleteClick(int position)
    {
        Athlete athlete = athleteList.get(position);
        int sportID = athlete.getSport();
        String fullName = athlete.getFirstName()+" "+athlete.getLastName();
        String sport = myDatabase.myDao().getSportNameById(sportID);

        getMatchesOfAthlete(fullName,sport);
    }

    private void getMatchesOfAthlete(String fullName, String sport) {
        db.collection("Matches")
                .document("SingleMatch")
                .collection("S_Matches")
                .whereEqualTo("sport",sport)
                .whereArrayContains("athletes",fullName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            SingleMatches singleMatch = new SingleMatches();
                            singleMatchesList = new ArrayList<SingleMatches>();

                            for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                            {
                                singleMatch = documentSnapshot.toObject(SingleMatches.class);
                                singleMatchesList.add(singleMatch);
                                Log.d("FIREBASE",documentSnapshot.getId()+" => "+documentSnapshot.getData());
                            }
                            //change to SingleMatchesAdapter
                            SingleMatchesAdapter adapter = new SingleMatchesAdapter(singleMatchesList);
                            setRecyclerLayout();
                            recyclerView.setAdapter(adapter);
                        }else {
                            Log.d("ERROR","Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onTeamClick(int position)
    {
        Team team = teamList.get(position);
        int sportId = team.getSport();
        String name = team.getName();
        String sport = myDatabase.myDao().getSportNameById(sportId);

        getMatchesOfTeam(name,sport);
    }

    private void getMatchesOfTeam(String name, String sport) {
        db.collection("Matches")
                .document("TeamMatch")
                .collection("T_Matches")
                .whereEqualTo("sport",sport)
                .whereArrayContains("teams",name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            TeamMatches teamMatch = new TeamMatches();
                            teamMatchesList = new ArrayList<TeamMatches>();

                            for(QueryDocumentSnapshot documentSnapshot : task.getResult())
                            {
                                teamMatch = documentSnapshot.toObject(TeamMatches.class);
                                teamMatchesList.add(teamMatch);
                                Log.d("FIREBASE",documentSnapshot.getId()+" => "+documentSnapshot.getData());
                            }
                            TeamMatchesAdapter adapter = new TeamMatchesAdapter(teamMatchesList);
                            setRecyclerLayout();
                            recyclerView.setAdapter(adapter);
                        }else {
                            Log.d("ERROR","Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    @Override
    public void onRemoveTeamMatchListener(int position) {
        TeamMatches teamMatch = teamMatchesList.get(position);

        db.collection("Matches")
                .document("TeamMatch")
                .collection("T_Matches")
                .document(""+teamMatch.getCode())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //TODO Steile ton pisw sta team matches
                        onDelete=false;
                        RefreshActivity();

                        Log.d("FIREBASE","Successfully deleted document");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ERROR","Error deleting document",e);
                    }
                });

    }
}