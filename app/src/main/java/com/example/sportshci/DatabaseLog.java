package com.example.sportshci;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
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
        List<Sport> sports = MainActivity.myDatabase.myDao().getSports();
        String result = "";

        for (Sport i:sports){
            int code = i.getCode();
            String name = i.getName();
            String type = i.getType();
            String gender = i.getGender();

            result = result + "\n Id: "+code+"\n Name: "+name+"\n Type: "+type+"\n Gender: "+gender+"\n";
        }
        textView.setText(result);
        return view;
    }
}