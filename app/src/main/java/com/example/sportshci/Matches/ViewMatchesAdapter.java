package com.example.sportshci.Matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Sports.SportsRecyclerAdapter;

public class ViewMatchesAdapter extends RecyclerView.Adapter<ViewMatchesAdapter.MyViewHolder> {

    public ViewMatchesAdapter()
    {

    }

    @NonNull
    @Override
    public ViewMatchesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_single_matches, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMatchesAdapter.MyViewHolder holder, int position) {

        holder.dateTxt.setText("name");
        holder.cityTxt.setText("name");
        holder.countryTxtS.setText("name");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView dateTxt,cityTxt,countryTxtS;

        public MyViewHolder(@NonNull View view)
        {
            super(view);
            dateTxt = view.findViewById(R.id.dateTxtS);
            cityTxt = view.findViewById(R.id.cityTxtS);
            countryTxtS = view.findViewById(R.id.countryTxtS);
        }

    }
}
