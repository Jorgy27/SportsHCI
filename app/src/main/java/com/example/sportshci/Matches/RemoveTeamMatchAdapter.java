package com.example.sportshci.Matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.AthletesAndTeams.RemoveTeamAdapter;
import com.example.sportshci.FirestoreDB.TeamMatches;
import com.example.sportshci.R;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class RemoveTeamMatchAdapter extends RecyclerView.Adapter<RemoveTeamMatchAdapter.MyViewHolder>{

    private List<TeamMatches> teamMatchesList;
    private RemoveTeamMatchAdapter.OnTeamMatchListener mOnTeamMatchListener;

    public RemoveTeamMatchAdapter(List<TeamMatches> teamMatchesList, RemoveTeamMatchAdapter.OnTeamMatchListener onTeamMatchListener) {
        this.teamMatchesList = teamMatchesList;
        this.mOnTeamMatchListener = onTeamMatchListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_remove_team_match, parent, false);
        return new RemoveTeamMatchAdapter.MyViewHolder(itemView,mOnTeamMatchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<String> teams = teamMatchesList.get(position).getTeams();
        List<Integer> scores = teamMatchesList.get(position).getScores();
        Date date = teamMatchesList.get(position).getDate();
        String city = teamMatchesList.get(position).getCity();
        String country = teamMatchesList.get(position).getCountry();

        String data = ""+teams.get(0)+" - "+teams.get(1)+"\n"
                    +scores.get(0)+" - "+ scores.get(1)+"\n"
                    +date.toString()+"\n"
                    +city+"\n"
                    +country+"\n";

        holder.match.setText(data);
    }

    @Override
    public int getItemCount() {
        return teamMatchesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView match;
        private  OnTeamMatchListener onRemoveTeamMatch;

        public MyViewHolder(@NonNull View itemView, OnTeamMatchListener mOnTeamMatchListener) {
            super(itemView);

            match = itemView.findViewById(R.id.removeTeamMatchTxt);

            this.onRemoveTeamMatch = mOnTeamMatchListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { onRemoveTeamMatch.onRemoveTeamMatchListener(getAdapterPosition());}
    }

    public interface OnTeamMatchListener {
        void onRemoveTeamMatchListener(int position);
    }

}
