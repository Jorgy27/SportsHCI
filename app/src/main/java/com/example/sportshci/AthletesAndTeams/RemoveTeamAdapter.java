package com.example.sportshci.AthletesAndTeams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.*;

import java.util.List;

public class RemoveTeamAdapter extends RecyclerView.Adapter<RemoveTeamAdapter.MyViewHolder>{

    private List<Team> teamList;
    private RemoveTeamAdapter.OnTeamListener mOnTeamListener;

    public RemoveTeamAdapter(List<Team> teamList, OnTeamListener onTeamListener) {
        this.teamList = teamList;
        this.mOnTeamListener = onTeamListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_remove_athlete_team, parent, false);
        return new MyViewHolder(itemView,mOnTeamListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String teamName = teamList.get(position).getName();

        holder.team.setText(teamName);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView team;
        private OnTeamListener onTeamListener;

        public MyViewHolder(@NonNull View itemView, OnTeamListener onTeamListener) {
            super(itemView);

            team = itemView.findViewById(R.id.removeTeamOrAthleteTxt);

            this.onTeamListener = onTeamListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { onTeamListener.onRemoveTeamListener(getAdapterPosition());}
    }

    public interface OnTeamListener {
        void onRemoveTeamListener(int position);
    }
}
