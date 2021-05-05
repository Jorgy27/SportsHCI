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

import java.util.List;

public class SingleMatchesAdapter  extends RecyclerView.Adapter<SingleMatchesAdapter.MyViewHolder> {

    private List<SingleMatches> singleMatchesList;
    private String sport;

    public SingleMatchesAdapter(List<SingleMatches> singleMatches)
    {
        this.singleMatchesList = singleMatches;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_single_matches, parent, false);
        return new SingleMatchesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
