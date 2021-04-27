package com.example.sportshci.Sports;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.Sport;

import java.util.List;

public class SportsRecyclerAdapter extends RecyclerView.Adapter<SportsRecyclerAdapter.MyViewHolder> {

    private List<Sport> sportList;
    public SportsRecyclerAdapter(List<Sport> sportList){
        this.sportList = sportList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt;
        private TextView typeTxt;
        private TextView genderTxt;

        public MyViewHolder(final View view){
            super(view);
            nameTxt = view.findViewById(R.id.nameTxt);
            typeTxt = view.findViewById(R.id.typeTxt);
            genderTxt = view.findViewById(R.id.genderTxt);
        }
    }

    @NonNull
    @Override
    public SportsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sports,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull SportsRecyclerAdapter.MyViewHolder holder, int position) {
        String name = sportList.get(position).getName();
        String type = sportList.get(position).getType();
        String gender = sportList.get(position).getGender();

        holder.nameTxt.setText(name);
        holder.typeTxt.setText(type);
        holder.genderTxt.setText(gender);
    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }
}
