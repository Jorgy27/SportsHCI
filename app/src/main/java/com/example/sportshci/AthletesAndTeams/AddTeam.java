package com.example.sportshci.AthletesAndTeams;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sportshci.R;
import com.example.sportshci.SideMenuActivity;

public class AddTeam extends Fragment {

    View view;
    Button button;

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

        InstantiateSubmitButton();

        return view;
    }

    void InstantiateSubmitButton()
    {
        button = view.findViewById(R.id.submitBtn);

        button.setOnClickListener((v) -> {
                    //TODO add team to database


                    SideMenuActivity activity =(SideMenuActivity) getActivity();
                    activity.RefreshActivity();
                }
        );
    }
}