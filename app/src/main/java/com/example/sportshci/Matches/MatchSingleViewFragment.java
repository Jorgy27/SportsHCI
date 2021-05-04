package com.example.sportshci.Matches;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sportshci.R;

public class MatchSingleViewFragment extends Fragment {

    View view;

    public MatchSingleViewFragment()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_single_matches, container, false);
        init();

        return view;
    }


    public void init()
    {
        TableLayout ll = (TableLayout) view.findViewById(R.id.displayLinear);

        for(int i =0; i<2;i++)
        {
            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView textView=new TextView(getContext());
            textView.setId(R.id.nameTxt);
            textView.setText("test1");
            row.addView(textView);
            ll.addView(row);
        }
    }
}
