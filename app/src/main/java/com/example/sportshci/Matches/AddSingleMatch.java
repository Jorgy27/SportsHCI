package com.example.sportshci.Matches;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sportshci.FirestoreDB.SingleMatches;
import com.example.sportshci.FirestoreDB.TeamMatches;
import com.example.sportshci.R;
import com.example.sportshci.Room.Athlete;
import com.example.sportshci.Room.Team;
import com.example.sportshci.SideMenuActivity;
import com.example.sportshci.Sports.RemoveSportsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class AddSingleMatch extends Fragment {

    EditText cityTxt,countryTxt;
    List<Athlete> athleteList;
    View view;
    int numberOfAthletes;
    DatePicker datePicker;
    TimePicker timePicker;
    Button submit;


    SideMenuActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_single_match, container, false);

        numberOfAthletes=getArguments().getInt("numberOfAthletes");

        setAddAthleteRecycler();

        datePicker = (DatePicker) view.findViewById(R.id.insertSingleMatchDate_picker);
        cityTxt = view.findViewById(R.id.insertSingleMatchCity);
        countryTxt = view.findViewById(R.id.insertSingleMatchCountry);
        timePicker = (TimePicker) view.findViewById(R.id.insertSingleMatchTime_picker);


        activity  = (SideMenuActivity) getActivity();
        activity.HideSideMenu();


        InstantiateSubmitButton();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void InstantiateSubmitButton() {
        submit = view.findViewById(R.id.insertSingleMatchSubmitBtn);

        submit.setOnClickListener((v -> {
            String[] scores = new String[numberOfAthletes];
            String[] athletes = new String[numberOfAthletes];

            String var_city = cityTxt.getText().toString();
            String var_country = countryTxt.getText().toString();
            String var_gender = SideMenuActivity.clickedSport.getGender();
            String var_sport = SideMenuActivity.clickedSport.getName();

            List<String> var_athletes = new ArrayList<String>();
            List<String> var_scores = new ArrayList<String>();

            for(int i =0; i<numberOfAthletes;i++){
                scores[i] = AddMatchAthleteAdapter.scoreTxt[i].getText().toString();

                var_scores.add(scores[i]);
                var_athletes.add(AddMatchAthleteAdapter.selectedNamesArray[i]);
            }

            Calendar calendar = new GregorianCalendar(datePicker.getMonth()
                    ,datePicker.getDayOfMonth()
                    ,datePicker.getYear()
                    ,timePicker.getHour()
                    ,timePicker.getMinute());
            Date var_dateTime = calendar.getTime();

            SideMenuActivity.db
                    .collection("Matches")
                    .document("SingleMatch")
                    .collection("S_Matches")
                    .orderBy("code", Query.Direction.DESCENDING).limit(1) //find the max code in the database
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                    {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                        {
                            int code;

                            if(queryDocumentSnapshots.isEmpty()){
                                code=0;
                            }else
                            {
                                List<SingleMatches> singleMatches = new ArrayList<>();
                                singleMatches = queryDocumentSnapshots.toObjects(SingleMatches.class); //get the document with the max code
                                code = singleMatches.get(0).getCode()+1;
                            }

                            SingleMatches singleMatch = new SingleMatches();
                            singleMatch.setCode(code);
                            singleMatch.setDate(var_dateTime);
                            singleMatch.setAthletes(var_athletes);
                            singleMatch.setScores(var_scores);
                            singleMatch.setGender(var_gender);
                            singleMatch.setCountry(var_country);
                            singleMatch.setCity(var_city);
                            singleMatch.setSport(var_sport);


                            insertSingleMatch(code,singleMatch);

                            //TODO kai auto
                            activity.RefreshActivity();
                        }

                    });

        }));
    }

    private void insertSingleMatch(int code, SingleMatches singleMatch)
    {
        SideMenuActivity.db
                .collection("Matches")
                .document("SingleMatch")
                .collection("S_Matches")
                .document(""+code)
                .set(singleMatch)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Single Match Added",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener((e)->{
                    Toast.makeText(getActivity(),"add match failed",Toast.LENGTH_LONG).show();
                });
    }

    private void setAddAthleteRecycler()
    {
        int sport = SideMenuActivity.myDatabase.myDao().getSportIdByName(SideMenuActivity.clickedSport.getName());
        athleteList = SideMenuActivity.myDatabase.myDao().getAthletesBySport(sport);

        AddMatchAthleteAdapter adapter = new AddMatchAthleteAdapter(athleteList,numberOfAthletes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerListInsertAthletes);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
    }
}