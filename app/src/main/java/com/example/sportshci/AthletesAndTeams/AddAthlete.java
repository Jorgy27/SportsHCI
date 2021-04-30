package com.example.sportshci.AthletesAndTeams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.SideMenuActivity;

public class AddAthlete extends Fragment {
    View view;
    Button button;

    public void AddAthlete()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_athlete, container, false);

        InstantiateSubmitButton();

        return view;
    }

    void InstantiateSubmitButton()
    {
        button = view.findViewById(R.id.submitBtn);

        button.setOnClickListener((v) -> {
                    //TODO add athlete to database

                    //Kanei restart to activity gia na ksanapaei sti lista me ta tous athlites kai tis omades
                    SideMenuActivity activity = (SideMenuActivity)getActivity();
                    activity.RefreshActivity();
                }
        );
    }


}