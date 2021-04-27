package com.example.sportshci.Sports;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.*;
import com.example.sportshci.Sports.Tests.DatabaseLog;


public class AddSport extends Fragment {

    EditText sportNameTxt, sportTypeTxt, sportGenderTxt;
    Button button;

    public AddSport() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_database, container, false);

        sportNameTxt = view.findViewById(R.id.sportNameTxt);
        sportTypeTxt = view.findViewById(R.id.sportTypeTxt);
        sportGenderTxt = view.findViewById(R.id.sportGenderTxt);
        button = view.findViewById(R.id.submitBtn);

        button.setOnClickListener( (v)->{
                String var_sportName = sportNameTxt.getText().toString();
                String var_sportType = sportTypeTxt.getText().toString();
                String var_sportGender = sportGenderTxt.getText().toString();

                Sport sport = new Sport();
                sport.setName(var_sportName);
                sport.setType(var_sportType);
                sport.setGender(var_sportGender);

                MainActivity.myDatabase.myDao().addSport(sport);
                Toast.makeText(getActivity(),"Ola kala bro mou ola popa",Toast.LENGTH_LONG).show();


                DatabaseLog databaseLogFragment = new DatabaseLog();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, databaseLogFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        );
        return view;
    }

}