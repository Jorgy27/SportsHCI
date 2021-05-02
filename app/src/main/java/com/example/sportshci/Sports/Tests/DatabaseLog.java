package com.example.sportshci.Sports.Tests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.*;

public class DatabaseLog extends Fragment {
    TextView textView;


    public DatabaseLog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_database_log, container, false);

        textView = view.findViewById(R.id.textView2);
        List<Team> teams = MainActivity.myDatabase.myDao().getTeams();
        String result = "";

        for (Team i:teams){
            int code = i.getCode();
            String name = i.getName();
            int type = i.getSport();

            result = result + "\n Id: "+code+"\n FName: "+name+"\n sport: "+type+"\n";
        }
        textView.setText(result);
        return view;
    }
}