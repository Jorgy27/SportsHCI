package com.example.sportshci.Matches;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        return view;
    }

    private void setAddAthleteRecycler()
    {
        int sport = SideMenuActivity.myDatabase.myDao().getSportIdByName(SideMenuActivity.clickedSport.getName());
        athleteList = SideMenuActivity.myDatabase.myDao().getAthletesBySport(sport);

        AddMatchAthleteAdapter adapter = new AddMatchAthleteAdapter(athleteList,numberOfAthletes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListAthletes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
    }
}