package com.corona.covid_19explorer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.corona.covid_19explorer.Classes.Country;
import com.corona.covid_19explorer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Country_List extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;
    DatabaseReference time;
    ArrayList<String> countries;
    ArrayAdapter<String> adapter;
    Country country;
    TextView timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country__list);
        listView = (ListView) findViewById(R.id.listView);
        country = new Country();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reff = firebaseDatabase.getReference().child("country");
        time = firebaseDatabase.getReference().child("timestamp");
        countries = new ArrayList<>();
        adapter = new ArrayAdapter<>(Country_List.this, R.layout.country_info, R.id.country_info_list, countries);
        timestamp = (TextView) findViewById(R.id.timestamp);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    country = ds.getValue(Country.class);
                    countries.add("Country:" + "\t" + country.getName().toString() + "\n" + "Total Cases:" + "\t" + country.getTotal() + "\n" + "New Cases:" + "\t" + country.getNewCases() + "\n" + "Total Deaths:" + "\t" + country.getTotalDeaths() + "\n" + "New Deaths:" + "\t" + country.getNewCases() + "\n" + "Total Recovered:" + "\t" + country.getTotalRecovered() + "\n" + "Active Cases:" + "\t" + country.getActiveCases() + "\n" + "Serious Cases:" + "\t" + country.getSeriousCases());
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        time.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String day = dataSnapshot.child("date").getValue().toString();
                timestamp.setText(day);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
