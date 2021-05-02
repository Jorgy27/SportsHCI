package com.example.sportshci.AthletesAndTeams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Sports.Tests.DatabaseLog;

public class AddAthlete extends Fragment {

    EditText nameTxt,surnameTxt,townTxt,countryTxt,sportTxt,birthdayTxt;
    Button submit;
    View view;
    public void AddAthlete()
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
        view = inflater.inflate(R.layout.fragment_add_athlete, container, false);

        nameTxt = view.findViewById(R.id.athleteNameTxt);
        surnameTxt = view.findViewById(R.id.athleteSurnameTxt);
        townTxt = view.findViewById(R.id.athleteTownTxt);
        countryTxt = view.findViewById(R.id.athleteCountryTxt);
        sportTxt = view.findViewById(R.id.athleteSportIDTxt);
        birthdayTxt = view.findViewById(R.id.athleteBirthTxt);

        submit = view.findViewById(R.id.athleteSubmitBtn);

        submit.setOnClickListener((v)->{
            String var_name = nameTxt.getText().toString();
            String var_surname = surnameTxt.getText().toString();
            String var_town = townTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_sport = sportTxt.getText().toString();
            String var_birth = birthdayTxt.getText().toString();
            int var_sportID = MainActivity.myDatabase.myDao().getSportIdByName(var_sport);

            Athlete athlete = new Athlete();
            athlete.setFirstName(var_name);
            athlete.setLastName(var_surname);
            athlete.setCity(var_town);
            athlete.setCountry(var_country);
            athlete.setSport(var_sportID);
            athlete.setBirthday(var_birth);

            MainActivity.myDatabase.myDao().addAthlete(athlete);

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