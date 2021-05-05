package com.example.sportshci.Matches;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.FirestoreDB.TeamMatches;
import com.example.sportshci.R;
import com.example.sportshci.SideMenuActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamMatchesAdapter extends RecyclerView.Adapter<TeamMatchesAdapter.MyViewHolder>{

    private List<TeamMatches> teamMatchesList;

    public TeamMatchesAdapter(List<TeamMatches> teamMatchesList) {
        this.teamMatchesList = teamMatchesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_team_matches, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Map<String,String> teams = teamMatchesList.get(position).getTeams();
        Map<String,Integer> scores = teamMatchesList.get(position).getScores();

        String team1 = teams.get("team1");
        String team2 = teams.get("team2");
        String score = (""+scores.get("team1")+" - "+scores.get("team2")+"").toString();
        String city = teamMatchesList.get(position).getCity();
        String country = teamMatchesList.get(position).getCountry();
        Date date = teamMatchesList.get(position).getDate();


        holder.team1Txt.setText(team1);
        holder.team2Txt.setText(team2);
        holder.scoreTxt.setText(score);
        holder.cityTxt.setText(city);
        holder.countryTxt.setText(country);
        holder.dateTxt.setText(date.toString());
    }

    @Override
    public int getItemCount() {
        return teamMatchesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView team1Txt,team2Txt,scoreTxt,cityTxt,countryTxt,dateTxt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            team1Txt = itemView.findViewById(R.id.team1Item);
            team2Txt = itemView.findViewById(R.id.team2Item);
            scoreTxt = itemView.findViewById(R.id.scoreItem);
            cityTxt = itemView.findViewById(R.id.cityItem);
            countryTxt = itemView.findViewById(R.id.countryItem);
            dateTxt = itemView.findViewById(R.id.dateItem);
        }
    }

}
