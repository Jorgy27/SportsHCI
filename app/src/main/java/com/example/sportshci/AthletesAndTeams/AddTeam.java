package com.example.sportshci.AthletesAndTeams;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Team;
import com.example.sportshci.Sports.Tests.DatabaseLog;

public class AddTeam extends Fragment {

    EditText nameTxt,stadiumTxt,townTxt,countryTxt,sportTxt,birthdayTxt;
    Button submit;
    View view;

    public void AddTeam()
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_team, container, false);

        nameTxt = view.findViewById(R.id.teamNameTxt);
        stadiumTxt = view.findViewById(R.id.teamStadiumTxt);
        townTxt = view.findViewById(R.id.teamTownTxt);
        countryTxt = view.findViewById(R.id.teamCountryTxt);
        sportTxt = view.findViewById(R.id.teamSportIDTxt);
        birthdayTxt = view.findViewById(R.id.teamYearTxt);

        submit = view.findViewById(R.id.teamSubmitBtn);

        submit.setOnClickListener((v)->{
            String var_name = nameTxt.getText().toString();
            String var_stadium = stadiumTxt.getText().toString();
            String var_town = townTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_sport = sportTxt.getText().toString();
            String var_birth = birthdayTxt.getText().toString();
            int var_sportId = MainActivity.myDatabase.myDao().getSportIdByName(var_sport);

            Team team = new Team();
            team.setName(var_name);
            team.setField(var_stadium);
            team.setCity(var_town);
            team.setCountry(var_country);
            team.setSport(var_sportId);
            team.setBirthday(var_birth);

            MainActivity.myDatabase.myDao().addTeam(team);

            /*
            DatabaseLog databaseLogFragment = new DatabaseLog();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, databaseLogFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            */
        });

        return view;
    }
}