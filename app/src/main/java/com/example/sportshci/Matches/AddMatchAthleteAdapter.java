package com.example.sportshci.Matches;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Sports.RemoveSportsAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddMatchAthleteAdapter extends RecyclerView.Adapter<AddMatchAthleteAdapter.MyViewHolder> implements AdapterView.OnItemSelectedListener {

    List<Athlete> athleteList;
    int maxAthletes;

    public AddMatchAthleteAdapter(List<Athlete> athleteList , int maxAthletes)
    {
        this.maxAthletes=maxAthletes;
        this.athleteList=athleteList;
    }

    @NonNull
    @NotNull
    @Override
    public AddMatchAthleteAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_add_athlete_to_match, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
        holder.score.setTag("singleMatchScore"+(position+1));
        holder.athletes.setTag("singleMatchNameSpinner"+(position+1));

        String[] athleteNames = new String[athleteList.size()];
        for(int i=0;i<athleteList.size();i++)
        {
            athleteNames[i]=athleteList.get(i).getFirstName();
        }

        CreateGenderDropDownSpinner(holder.athletes,athleteNames,holder.itemView);
    }

    @Override
    public int getItemCount() {
        return maxAthletes;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private Spinner athletes;
        private EditText score;
        public MyViewHolder(final View view) {
            super(view);
            athletes = view.findViewById(R.id.singleMatchNameSpinner);
            score = view.findViewById(R.id.singleMatchScore);
        }
    }

    void CreateGenderDropDownSpinner(Spinner athleteDropDown,String[] items,View view)
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_item, items);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, items);
        athleteDropDown.setAdapter(adapter);
        athleteDropDown.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //athlete=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
