package com.example.sportshci.AthletesAndTeams;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.MyDatabase;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.Room.Team;
import com.example.sportshci.SideMenuActivity;
import com.example.sportshci.Sports.Tests.DatabaseLog;

import java.util.List;

public class AddTeam extends Fragment implements AdapterView.OnItemSelectedListener{

    EditText nameTxt,stadiumTxt,townTxt,countryTxt,sportTxt,birthdayTxt;
    Button submit;
    View view;
    List<Sport> sportList;
    Integer sportID;

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

        sportList = SideMenuActivity.myDatabase.myDao().getSports();

        nameTxt = view.findViewById(R.id.teamNameTxt);
        stadiumTxt = view.findViewById(R.id.teamStadiumTxt);
        townTxt = view.findViewById(R.id.teamTownTxt);
        countryTxt = view.findViewById(R.id.teamCountryTxt);
        //sportTxt = view.findViewById(R.id.teamSportIDTxt);
        birthdayTxt = view.findViewById(R.id.teamYearTxt);

        CreateSportsDropDownSpinner(); //gemizei to dropdown gia ta types

        InstantiateSubmitButton();

        return view;
    }

    void InstantiateSubmitButton()
    {
        submit = view.findViewById(R.id.teamSubmitBtn);

        submit.setOnClickListener((v)->{
            if(sportID==null)
            {
                Toast.makeText(getContext(),"List of sports is empty",Toast.LENGTH_LONG).show();
                return;
            }

            String var_name = nameTxt.getText().toString();
            String var_stadium = stadiumTxt.getText().toString();
            String var_town = townTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_birth = birthdayTxt.getText().toString();
            //String var_sport = sportTxt.getText().toString();
            //int var_sportId = MainActivity.myDatabase.myDao().getSportIdByName(var_sport);

            Team team = new Team();
            team.setName(var_name);
            team.setField(var_stadium);
            team.setCity(var_town);
            team.setCountry(var_country);
            //team.setSport(var_sportId);
            team.setSport(sportID);
            team.setBirthday(var_birth);

            MainActivity.myDatabase.myDao().addTeam(team);

            SideMenuActivity activity =(SideMenuActivity) getActivity();
            activity.RefreshActivity();

            /*
            DatabaseLog databaseLogFragment = new DatabaseLog();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, databaseLogFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            */
        });
    }

    void CreateSportsDropDownSpinner()
    {
        Spinner dropdown = view.findViewById(R.id.teamSportNameSpinner);
        String[] items = new String[sportList.size()];
        for(int i=0;i<items.length;i++)
        {
            items[i]=sportList.get(i).getName();
        }
        //String[] items = new String[]{"Male","Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.teamSportNameSpinner)
        {
            sportID=sportList.get(position).getCode();
            //genderText=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId()==R.id.teamSportNameSpinner)
        {
            sportID = null;
        }
    }
}