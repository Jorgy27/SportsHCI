package com.example.sportshci.AthletesAndTeams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.*;
import com.example.sportshci.SideMenuActivity;

import java.util.List;

public class TeamTableAdapter extends RecyclerView.Adapter<TeamTableAdapter.MyViewHolder>{


    private List<Team> teamList;
    private TeamTableAdapter.OnTeamListener mOnTeamListener;

    public TeamTableAdapter(List<Team> teamList, TeamTableAdapter.OnTeamListener onTeamListener) {
        this.teamList = teamList;
        this.mOnTeamListener = onTeamListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_teams_list, parent, false);
        return new TeamTableAdapter.MyViewHolder(itemView, mOnTeamListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String teamName = teamList.get(position).getName();
        String teamStadium = teamList.get(position).getField();
        String teamCity = teamList.get(position).getCity();
        String teamCountry = teamList.get(position).getCountry();
        String teamBirth = teamList.get(position).getBirthday();
        int teamSportId = teamList.get(position).getSport();
        String teamSport = SideMenuActivity.myDatabase.myDao().getSportNameById(teamSportId);

        holder.nameTxt.setText(teamName);
        holder.stadiumTxt.setText(teamStadium);
        holder.cityTxt.setText(teamCity);
        holder.countryTxt.setText(teamCountry);
        holder.sportTxt.setText(teamSport);
        holder.birthTxt.setText(teamBirth);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nameTxt, stadiumTxt, cityTxt, countryTxt, birthTxt, sportTxt;
        private TeamTableAdapter.OnTeamListener onTeamListener;

        public MyViewHolder(final View view, OnTeamListener onTeamListener){
            super(view);
            nameTxt= view.findViewById(R.id.teamNameItem);
            stadiumTxt = view.findViewById(R.id.teamFieldItem);
            cityTxt = view.findViewById(R.id.teamCityItem);
            countryTxt = view.findViewById(R.id.teamCountryItem);
            birthTxt = view.findViewById(R.id.teamBirthdayItem);
            sportTxt = view.findViewById(R.id.teamSportItem);

            this.onTeamListener = onTeamListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onTeamListener.onTeamClick(getAdapterPosition());
        }
    }

    public interface OnTeamListener {
        void onTeamClick(int position);
    }

}
