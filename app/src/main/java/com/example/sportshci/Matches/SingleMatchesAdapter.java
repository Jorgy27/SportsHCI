package com.example.sportshci.Matches;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sportshci.FirestoreDB.SingleMatches;
import com.example.sportshci.R;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SingleMatchesAdapter  extends RecyclerView.Adapter<SingleMatchesAdapter.MyViewHolder> {

    private List<SingleMatches> singleMatchesList;
    private OnSingleMatchListener onSingleMatchListener;

    public SingleMatchesAdapter(List<SingleMatches> singleMatches,OnSingleMatchListener onSingleMatchListener)
    {
        this.singleMatchesList = singleMatches;
        this.onSingleMatchListener = onSingleMatchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_single_matches, parent, false);
        return new SingleMatchesAdapter.MyViewHolder(itemView,onSingleMatchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<String> athletes = singleMatchesList.get(position).getAthletes();
        List<String> scores = singleMatchesList.get(position).getScores();

        int count = athletes.size();
        String allAthletesWithScores = "";

        for(int i=0;i<count;i++){
            allAthletesWithScores += athletes.get(i)+" : "+scores.get(i)+"\n";
        }
        String city = singleMatchesList.get(position).getCity();
        String country = singleMatchesList.get(position).getCountry();
        Date date = singleMatchesList.get(position).getDate();

        holder.athletesTxt.setText(allAthletesWithScores);
        holder.dateTxt.setText(date.toString());
        holder.cityTxt.setText(city);
        holder.countryTxt.setText(country);
    }

    @Override
    public int getItemCount() {
        return singleMatchesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView athletesTxt,cityTxt,countryTxt,dateTxt;
        private OnSingleMatchListener onSingleMatchListener;
        public MyViewHolder(@NonNull View itemView,OnSingleMatchListener onSingleMatchListener)
        {
            super(itemView);
            athletesTxt = itemView.findViewById(R.id.athletesWithScore);
            cityTxt = itemView.findViewById(R.id.cityItemS);
            countryTxt = itemView.findViewById(R.id.countryItemS);
            dateTxt = itemView.findViewById(R.id.dateItemS);

            this.onSingleMatchListener=onSingleMatchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSingleMatchListener.OnSingleMatchClick();
        }
    }

    public interface OnSingleMatchListener{
        void OnSingleMatchClick();
    }
}
