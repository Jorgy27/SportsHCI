package com.example.sportshci.Sports.Tests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sportshci.MainActivity;
import com.example.sportshci.R;
import com.example.sportshci.Room.*;
import com.example.sportshci.SideMenuActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class DatabaseLog extends Fragment {
    TextView textView;
    DocumentReference documentReference;
    public DatabaseLog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_database_log, container, false);

        textView = view.findViewById(R.id.textView2);
        documentReference = SideMenuActivity.db.collection("Matches").
                document("SingleMatch").
                collection("S_Matches").
                document("0");

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Map athletes =(HashMap<String, Integer>)documentSnapshot.get("athletes");
                    double code =documentSnapshot.getDouble("code");
                    textView.setText("code: "+code+"\n athletes: " + athletes+"\n");
                }else {
                    Toast.makeText(getActivity(), "document does not exist",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener((e)->{
            Toast.makeText(getActivity(), "document failed",Toast.LENGTH_LONG).show();
        });
        return view;
    }
}