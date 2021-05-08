package com.example.sportshci.Matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.FirestoreDB.SingleMatches;
import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemoveSingleMatchAdapter extends RecyclerView.Adapter<RemoveSingleMatchAdapter.MyViewHolder>{

    private List<SingleMatches> singleMatchesList;
    private OnSingleMatchListener onSingleMatchListener;

    public RemoveSingleMatchAdapter(List<SingleMatches> singleMatchesList, OnSingleMatchListener onSingleMatchListener) {
        this.singleMatchesList = singleMatchesList;
        this.onSingleMatchListener = onSingleMatchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_remove_single_match, parent, false);
        return new RemoveSingleMatchAdapter.MyViewHolder(itemView,onSingleMatchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<String> athletes = singleMatchesList.get(position).getAthletes();
        List<String> scores = singleMatchesList.get(position).getScores();
        Date date = singleMatchesList.get(position).getDate();
        String city = singleMatchesList.get(position).getCity();
        String country = singleMatchesList.get(position).getCountry();
        String athletesWithScore="";
        for(int i =0; i<=athletes.size()-1;i++){
            athletesWithScore+= ""+athletes.get(i) +" - " + scores.get(i)+"\n";
        }
        String data = athletesWithScore
                +date.toString()+"\n"
                +city+"\n"
                +country+"\n";

        holder.match.setText(data);
    }

    @Override
    public int getItemCount() {
        return singleMatchesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView match;
        private OnSingleMatchListener onSingleMatchListener;

        public MyViewHolder(View itemView, OnSingleMatchListener onSingleMatchListener) {
            super(itemView);

            match = itemView.findViewById(R.id.removeSingleMatchTxt);

            this.onSingleMatchListener = onSingleMatchListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onSingleMatchListener.onRemoveSingleMatchListener(getAdapterPosition());
        }
    }

    public interface OnSingleMatchListener{
        void onRemoveSingleMatchListener(int position);
    }
}
