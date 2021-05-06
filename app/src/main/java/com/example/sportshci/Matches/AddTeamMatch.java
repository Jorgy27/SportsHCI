package com.example.sportshci.Matches;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sportshci.FirestoreDB.TeamMatches;
import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Room.Team;
import com.example.sportshci.SideMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddTeamMatch extends  Fragment implements AdapterView.OnItemSelectedListener{
    EditText townTxt,countryTxt,score1Txt,score2Txt;
    DatePicker datePicker;
    TimePicker timePicker;
    Button submit;
    View view;
    List<Team> teamList;
    String team1Name,team2Name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_team_match, container, false);

        townTxt = view.findViewById(R.id.insertTeamMatchCity);
        countryTxt = view.findViewById(R.id.insertTeamMatchCountry);
        score1Txt = view.findViewById(R.id.insertTeamMatchScore1);
        score2Txt = view.findViewById(R.id.insertTeamMatchScore2);
        datePicker = (DatePicker) view.findViewById(R.id.insertTeamMatchDate_picker);
        timePicker = (TimePicker) view.findViewById(R.id.insertTeamMatchTime_picker);

        CreateTeamsDropDownSpinner();
        InstantiateSubmitButton();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void InstantiateSubmitButton() {
        submit = view.findViewById(R.id.insertTeamMatchSubmitBtn);

        submit.setOnClickListener((v -> {
            int var_score1=0;
            int var_score2=0;

            String var_city = townTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_score1Txt = score1Txt.getText().toString();
            String var_score2Txt = score2Txt.getText().toString();
            String var_gender = SideMenuActivity.clickedSport.getGender();
            String var_sport = SideMenuActivity.clickedSport.getName();

            if(var_score1Txt!="" && var_score2Txt!=""){
                var_score1 = Integer.parseInt(var_score1Txt);
                var_score2 = Integer.parseInt(var_score2Txt);
            }

            Calendar calendar = new GregorianCalendar(datePicker.getMonth()
                    ,datePicker.getDayOfMonth()
                    ,datePicker.getYear()
                    ,timePicker.getHour()
                    ,timePicker.getMinute());
            Date var_dateTime = calendar.getTime();

            Map<String,String> var_teams = new HashMap<String,String>();
            Map<String,Integer> var_scores = new HashMap<String,Integer>();

            var_teams.put("team1",team1Name);
            var_teams.put("team2",team2Name);
            var_scores.put("team1",var_score1);
            var_scores.put("team2",var_score2);

            SideMenuActivity.db
                    .collection("Matches")
                    .document("TeamMatch")
                    .collection("T_Matches")
                    .orderBy("code", Query.Direction.DESCENDING).limit(1)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                    {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                        {

                            List<TeamMatches> team = new ArrayList<>();
                            team = queryDocumentSnapshots.toObjects(TeamMatches.class);
                            int code = team.get(0).getCode()+1;

                            TeamMatches teamMatch = new TeamMatches();
                            teamMatch.setCode(code);
                            teamMatch.setSport(var_sport);
                            teamMatch.setCity(var_city);
                            teamMatch.setCountry(var_country);
                            teamMatch.setGender(var_gender);
                            teamMatch.setDate(var_dateTime);
                            teamMatch.setTeams(var_teams);
                            teamMatch.setScores(var_scores);

                            insertTeamMatch(code,teamMatch);
                        }

                    });
        }));


    }

    private void insertTeamMatch(int code,TeamMatches teamMatch){
        SideMenuActivity.db
                .collection("Matches")
                .document("TeamMatch")
                .collection("T_Matches")
                .document(""+code)
                .set(teamMatch)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Team Match Added",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener((e)->{
                    Toast.makeText(getActivity(),"add team match failed",Toast.LENGTH_LONG).show();
                });
    }

    private void CreateTeamsDropDownSpinner() {


        int sport = SideMenuActivity.myDatabase.myDao().getSportIdByName(SideMenuActivity.clickedSport.getName());
        teamList = SideMenuActivity.myDatabase.myDao().getTeamsBySport(sport);

        Spinner team1Dropdown = view.findViewById(R.id.insertTeamMatchTeam1Spinner);
        Spinner team2Dropdown = view.findViewById(R.id.insertTeamMatchTeam2Spinner);
        String[] items = new String[teamList.size()];

        for(int i=0;i<items.length;i++)
        {
            items[i]=teamList.get(i).getName();
        }
        //String[] items = new String[]{"Male","Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        team1Dropdown.setAdapter(adapter);
        team1Dropdown.setOnItemSelectedListener(this);

        team2Dropdown.setAdapter(adapter);
        team2Dropdown.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.insertTeamMatchTeam1Spinner)
        {
            team1Name=teamList.get(position).getName();
            //genderText=parent.getItemAtPosition(position).toString();
        }else if(parent.getId()==R.id.insertTeamMatchTeam2Spinner)
        {
            team2Name = teamList.get(position).getName();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId()==R.id.insertTeamMatchTeam1Spinner)
        {
            team1Name=null;
            //genderText=parent.getItemAtPosition(position).toString();
        }else if(parent.getId()==R.id.insertTeamMatchTeam2Spinner)
        {
            team2Name = null;
        }
    }
}