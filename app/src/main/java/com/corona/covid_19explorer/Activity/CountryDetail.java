package com.corona.covid_19explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.corona.covid_19explorer.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class CountryDetail extends AppCompatActivity {

    TextView total,cured,active,death;
    PieChart pieChart;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        total = (TextView) findViewById(R.id.total);
        cured = (TextView) findViewById(R.id.cured);
        active = (TextView) findViewById(R.id.active);
        death = (TextView) findViewById(R.id.death);
        pieChart = (PieChart) findViewById(R.id.piechart);

        Intent intent = getIntent();
        total.setText(intent.getStringExtra("total"));
        cured.setText(intent.getStringExtra("cured"));
        active.setText(intent.getStringExtra("active"));
        death.setText(intent.getStringExtra("death"));
        name = intent.getStringExtra("name");

        getSupportActionBar().setTitle(name + " Stats");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pieChart.addPieSlice(new PieModel("total", Integer.parseInt(total.getText().toString()), Color.BLUE));
        pieChart.addPieSlice(new PieModel("cured", Integer.parseInt(cured.getText().toString()), Color.parseColor("#FFA726")));
        pieChart.addPieSlice(new PieModel("active", Integer.parseInt(active.getText().toString()), Color.BLACK));
        pieChart.addPieSlice(new PieModel("death", Integer.parseInt(death.getText().toString()), Color.RED));
        pieChart.startAnimation();
    }
}