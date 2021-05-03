package com.example.sportshci.AthletesAndTeams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.SideMenuActivity;

import java.util.List;

public class AthleteTableAdapter extends RecyclerView.Adapter<AthleteTableAdapter.MyViewHolder> {

    private final AthleteTableAdapter.OnAthleteListener mOnAthleteListener;
    private List<Athlete> athleteList;

    public AthleteTableAdapter(List<Athlete> athleteList, AthleteTableAdapter.OnAthleteListener OnAthleteListener) {
        this.athleteList = athleteList;
        this.mOnAthleteListener = OnAthleteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_athletes_list, parent, false);
        return new AthleteTableAdapter.MyViewHolder(itemView, mOnAthleteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String athleteFName = athleteList.get(position).getFirstName();
        String athleteSurname = athleteList.get(position).getLastName();
        String athleteCity = athleteList.get(position).getCity();
        String athleteCountry = athleteList.get(position).getCountry();
        String athleteBirth = athleteList.get(position).getBirthday();
        int athleteSportId = athleteList.get(position).getSport();
        String athleteSport = SideMenuActivity.myDatabase.myDao().getSportNameById(athleteSportId);

        holder.nameTxt.setText(athleteFName);
        holder.surnameTxt.setText(athleteSurname);
        holder.cityTxt.setText(athleteCity);
        holder.countryTxt.setText(athleteCountry);
        holder.birthTxt.setText(athleteBirth);
        holder.sportTxt.setText(athleteSport);
    }

    @Override
    public int getItemCount() {
        return athleteList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameTxt, surnameTxt, cityTxt, countryTxt, birthTxt, sportTxt;
        private AthleteTableAdapter.OnAthleteListener onAthleteListener;

        public MyViewHolder(final View view, AthleteTableAdapter.OnAthleteListener onAthleteListener) {
            super(view);
            nameTxt = view.findViewById(R.id.athleteNameItem);
            surnameTxt = view.findViewById(R.id.athleteSurnameItem);
            cityTxt = view.findViewById(R.id.athleteCityItem);
            countryTxt = view.findViewById(R.id.athleteCountryItem);
            birthTxt = view.findViewById(R.id.athleteBirthdayItem);
            sportTxt = view.findViewById(R.id.athleteSportItem);

            this.onAthleteListener = onAthleteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAthleteListener.onAthleteClick(getAdapterPosition());
        }
    }

    public interface OnAthleteListener {
        void onAthleteClick(int position);
    }
}