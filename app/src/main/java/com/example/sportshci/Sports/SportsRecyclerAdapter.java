package com.example.sportshci.Sports;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.Sport;

import java.util.List;

public class SportsRecyclerAdapter extends RecyclerView.Adapter<SportsRecyclerAdapter.MyViewHolder> {

    private List<Sport> sportList;
    private OnSportListener mOnSportListener;

    public SportsRecyclerAdapter(List<Sport> sportList, OnSportListener onSportListener) {
        this.sportList = sportList;
        this.mOnSportListener = onSportListener;
    }

    @NonNull
    @Override
    public SportsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sports, parent, false);
        return new MyViewHolder(itemView, mOnSportListener);
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

    //Kathe item einai kai ena ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameTxt;
        private TextView typeTxt;
        private TextView genderTxt;
        private OnSportListener onSportListener;

        public MyViewHolder(final View view, OnSportListener onSportListener) {
            super(view);
            nameTxt = view.findViewById(R.id.nameTxt);
            typeTxt = view.findViewById(R.id.typeTxt);
            genderTxt = view.findViewById(R.id.genderTxt);

            this.onSportListener = onSportListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSportListener.onSportClick(getAdapterPosition());
        }
    }

    public interface OnSportListener{
        void onSportClick(int position);
    }

}
