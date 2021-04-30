package com.example.sportshci.Sports;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportshci.R;
import com.example.sportshci.Room.Sport;
import com.example.sportshci.SideMenuActivity;

import java.util.List;

public class RemoveSportsAdapter extends RecyclerView.Adapter<RemoveSportsAdapter.MyViewHolder>{

    private List<Sport> sportList;
    private OnSportListener mOnSportListener;

    public RemoveSportsAdapter(List<Sport> sportList , OnSportListener onSportListener) {
        this.sportList = sportList;
        this.mOnSportListener = onSportListener;
    }

    @NonNull
    @Override
    public RemoveSportsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_remove_sport, parent, false);
        return new MyViewHolder(itemView,mOnSportListener);
    }


    @Override
    public void onBindViewHolder(@NonNull RemoveSportsAdapter.MyViewHolder holder, int position) {
        String name = sportList.get(position).getName();
        holder.sportName.setText(name);
    }

    @Override
    public int getItemCount() {
        return sportList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView sportName;
        private OnSportListener onSportListener;

        public MyViewHolder(final View view, OnSportListener onSportListener) {
            super(view);
            sportName = view.findViewById(R.id.removeNameTxt);

            this.onSportListener = onSportListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onSportListener.onRemoveSportClick(getAdapterPosition());
        }
    }

    public interface OnSportListener{
        void onRemoveSportClick(int position);
    }
}
