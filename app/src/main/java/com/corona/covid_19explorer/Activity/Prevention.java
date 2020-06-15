package com.corona.covid_19explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.corona.covid_19explorer.R;

public class Prevention extends AppCompatActivity {

    CardView symptoms,prevent;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);
        symptoms = (CardView) findViewById(R.id.symptoms_card);
        prevent = (CardView) findViewById(R.id.prevent_card);
        start = (Button) findViewById(R.id.startBtn);

        getSupportActionBar().setTitle("Prevention");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prevention.this, Symptoms.class);
                startActivity(intent);
            }
        });

        prevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prevention.this, Prevent.class);
                startActivity(intent);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prevention.this, CoughTest.class);
                startActivity(intent);
            }
        });
    }
}
