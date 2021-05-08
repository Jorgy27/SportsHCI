package com.example.sportshci.Matches;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Room.Team;
import com.example.sportshci.SideMenuActivity;
import com.example.sportshci.Sports.RemoveSportsAdapter;

import java.util.List;


public class AddSingleMatch extends Fragment {

    List<Athlete> athleteList;
    View view;
    int numberOfAthletes;
    DatePicker datePicker;
    TimePicker timePicker;
    Button submit;


    public AddSingleMatch(int numberOfAthletes) {
        this.numberOfAthletes=numberOfAthletes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_single_match, container, false);

        setAddAthleteRecycler();
        InstantiateSubmitButton();
        return view;
    }

    private void InstantiateSubmitButton() {
        submit = view.findViewById(R.id.insertSingleMatchSubmitBtn);
    }

    private void setAddAthleteRecycler()
    {
        int sport = SideMenuActivity.myDatabase.myDao().getSportIdByName(SideMenuActivity.clickedSport.getName());
        athleteList = SideMenuActivity.myDatabase.myDao().getAthletesBySport(sport);

        AddMatchAthleteAdapter adapter = new AddMatchAthleteAdapter(athleteList,numberOfAthletes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListInsertAthletes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
    }
}