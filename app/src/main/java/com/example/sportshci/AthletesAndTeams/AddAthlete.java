package com.example.sportshci.AthletesAndTeams;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.SideMenuActivity;
import com.example.sportshci.Sports.Tests.DatabaseLog;

import java.util.List;

public class AddAthlete extends Fragment implements AdapterView.OnItemSelectedListener{

    EditText nameTxt,surnameTxt,townTxt,countryTxt,sportTxt,birthdayTxt;
    Button submit;
    View view;
    List<Sport> sportList;
    Integer sportID;

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

        sportList = SideMenuActivity.myDatabase.myDao().getSports();

        nameTxt = view.findViewById(R.id.athleteNameTxt);
        surnameTxt = view.findViewById(R.id.athleteSurnameTxt);
        townTxt = view.findViewById(R.id.athleteTownTxt);
        countryTxt = view.findViewById(R.id.athleteCountryTxt);
        //sportTxt = view.findViewById(R.id.athleteSportIDTxt);
        birthdayTxt = view.findViewById(R.id.athleteBirthTxt);

        CreateSportsDropDownSpinner(); //gemizei to dropdown gia ta athlimata apo tin basi

        InstantiateSubmitButton();

        return view;
    }

    void InstantiateSubmitButton()
    {
        submit = view.findViewById(R.id.athleteSubmitBtn);

        submit.setOnClickListener((v)->{
            if(sportID==null)
            {
                Toast.makeText(getContext(),"List of sports is empty",Toast.LENGTH_LONG).show();
                return;
            }

            String var_name = nameTxt.getText().toString();
            String var_surname = surnameTxt.getText().toString();
            String var_town = townTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_birth = birthdayTxt.getText().toString();
            int var_sportID=sportID;
            //String var_sport = sportTxt.getText().toString();
            //int var_sportID = MainActivity.myDatabase.myDao().getSportIdByName(var_sport);


            Athlete athlete = new Athlete();
            athlete.setFirstName(var_name);
            athlete.setLastName(var_surname);
            athlete.setCity(var_town);
            athlete.setCountry(var_country);
            athlete.setSport(var_sportID);
            athlete.setBirthday(var_birth);

            MainActivity.myDatabase.myDao().addAthlete(athlete);

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
        Spinner dropdown = view.findViewById(R.id.athleteSportNameSpinner);
        String[] items = new String[sportList.size()];
        for(int i=0;i<items.length;i++)
        {
            items[i]=sportList.get(i).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        //dropdown.
        dropdown.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.athleteSportNameSpinner)
        {
            sportID=sportList.get(position).getCode();
            //genderText=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId()==R.id.athleteSportNameSpinner)
        {
            sportID=null;
        }
    }
}