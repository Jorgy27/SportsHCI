package com.example.sportshci.Sports;

import android.content.Intent;
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
import com.example.sportshci.Room.*;
import com.example.sportshci.SideMenuActivity;
import com.example.sportshci.Sports.Tests.DatabaseLog;


public class AddSport extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText sportNameTxt, sportTypeTxt, sportGenderTxt;
    Button button;
    View view;
    SideMenuActivity activity;

    String genderText ,typeText;

    public AddSport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test_database, container, false);

        sportNameTxt = view.findViewById(R.id.sportNameTxt);

        activity  = (SideMenuActivity) getActivity();
        activity.HideSideMenu();

        CreateGenderDropDownSpinner(); //gemizei to dropdown gia ta genders
        CreateTypeDropDownSpinner(); //gemizei to dropdown gia ta types
        InstantiateSubmitButton();//kanei litourgiko to submit button

        return view;
    }

    void InstantiateSubmitButton()
    {
        button = view.findViewById(R.id.submitBtn);

        button.setOnClickListener((v) -> {
                    String var_sportName = sportNameTxt.getText().toString();
                    String var_sportType = typeText;
                    String var_sportGender = genderText;

                    Sport sport = new Sport();
                    sport.setName(var_sportName);
                    sport.setType(var_sportType);
                    sport.setGender(var_sportGender);

                    MainActivity.myDatabase.myDao().addSport(sport);
                    /*
                    Toast.makeText(getActivity(),"Ola kala bro mou ola popa",Toast.LENGTH_LONG).show();
                    DatabaseLog databaseLogFragment = new DatabaseLog();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragment_container, databaseLogFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                     */

                    //Kanei restart to activity gia na ksanapaei sti lista me ta athlimata
                    activity.RefreshActivity();

                }
        );
    }

    void CreateGenderDropDownSpinner()
    {
        Spinner dropdown = view.findViewById(R.id.sportGenderSpinner);
        String[] items = new String[]{"Male","Female"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    void CreateTypeDropDownSpinner()
    {
        Spinner dropdown = view.findViewById(R.id.sportTypeSpinner);
        String[] items = new String[]{"Single","Team"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.sportGenderSpinner)
        {
            genderText=parent.getItemAtPosition(position).toString();
        }
        if(parent.getId()==R.id.sportTypeSpinner)
        {
            typeText=parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if(parent.getId()==R.id.sportGenderSpinner)
        {
            genderText="Male";
        }
        if(parent.getId()==R.id.sportTypeSpinner)
        {
            typeText = "Single";
        }
    }
}