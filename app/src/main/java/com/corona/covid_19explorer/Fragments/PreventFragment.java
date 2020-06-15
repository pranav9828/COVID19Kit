package com.corona.covid_19explorer.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.corona.covid_19explorer.Activity.CoughTest;
import com.corona.covid_19explorer.Activity.Prevent;
import com.corona.covid_19explorer.Activity.Prevention;
import com.corona.covid_19explorer.Activity.Symptoms;
import com.corona.covid_19explorer.R;

public class PreventFragment extends Fragment {
    CardView symptoms,prevent;
    Button start;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_prevent, container, false);
        symptoms = (CardView) view.findViewById(R.id.symptoms_card);
        prevent = (CardView) view.findViewById(R.id.prevent_card);
        start = (Button) view.findViewById(R.id.startBtn);
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Symptoms.class);
                startActivity(intent);
            }
        });

        prevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Prevent.class);
                startActivity(intent);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CoughTest.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
