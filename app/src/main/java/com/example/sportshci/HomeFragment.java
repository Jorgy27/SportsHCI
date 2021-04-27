package com.example.sportshci;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    private AppCompatImageButton sportBtn;
    private AppCompatImageButton addBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false); //krataw to view prin to epistrepsw


        //--------------- Button on Click Function ---------------

        sportBtn = view.findViewById(R.id.sportsBtn); //mesa apo to view vriskw to button
        sportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSideMenuActivity("Sports");
            }
        });
        /*
        addBtn = view.findViewById(R.id.athletesBtn); //mesa apo to view vriskw to button
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSport testDatabase = new AddSport();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, testDatabase);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/
        return view;
    }
    public void openSideMenuActivity(String value){
        Intent intent = new Intent(getActivity(),SideMenuActivity.class);
        intent.putExtra("sideMenu",value);
        startActivity(intent);
    }
}