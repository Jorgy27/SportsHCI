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

public class RemoveAthletesAdapter extends RecyclerView.Adapter<RemoveAthletesAdapter.MyViewHolder>{
    private List<Athlete> athletesList;
    private RemoveAthletesAdapter.OnAthleteListener mOnAthleteListener;

    public RemoveAthletesAdapter(List<Athlete> athletesList, OnAthleteListener onAthleteListener) {
        this.athletesList = athletesList;
        this.mOnAthleteListener = onAthleteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_remove_athlete_team, parent, false);
        return new MyViewHolder(itemView,mOnAthleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String firstName = athletesList.get(position).getFirstName();
        String lastName = athletesList.get(position).getLastName();
        String fullName = firstName+" "+lastName;

        holder.athlete.setText(fullName);
    }

    @Override
    public int getItemCount() {
        return athletesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView athlete;
        private OnAthleteListener onAthleteListener;

        public MyViewHolder(final View view, OnAthleteListener onAthleteListener) {
            super(view);
            athlete = view.findViewById(R.id.removeTeamOrAthleteTxt);

            this.onAthleteListener = onAthleteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) { onAthleteListener.onRemoveAthleteClick(getAdapterPosition()); }
    }

    public interface OnAthleteListener {
        void onRemoveAthleteClick(int position);
    }

}
