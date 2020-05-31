package com.corona.covid_19explorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.corona.covid_19explorer.EmergencyContact;
import com.corona.covid_19explorer.Ideas;
import com.corona.covid_19explorer.Quarantine;
import com.corona.covid_19explorer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView recovered_num, confirmed_num;
    CardView donation_card, prevent_card, ideas_card, news_card, contact_card, quarantine_card, reminder_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recovered_num = (TextView) findViewById(R.id.recovered_num);
        confirmed_num = (TextView) findViewById(R.id.confirmed_num);

        donation_card = (CardView) findViewById(R.id.donations_card);
        prevent_card = (CardView) findViewById(R.id.prevent_card);
        ideas_card = (CardView) findViewById(R.id.ideas_card);
        news_card = (CardView) findViewById(R.id.news_card);
        contact_card = (CardView) findViewById(R.id.contact_card);
        quarantine_card = (CardView) findViewById(R.id.quarantine_card);
        reminder_card = (CardView) findViewById(R.id.remind_card);

        donation_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Donations.class);
                startActivity(intent);
            }
        });

        prevent_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Prevention.class);
                startActivity(intent);
            }
        });

        ideas_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Ideas.class);
                startActivity(intent);
            }
        });

        news_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, CoronaNews.class);
                startActivity(intent);
            }
        });

        contact_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, EmergencyContact.class);
                startActivity(intent);
            }
        });

        quarantine_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Quarantine.class);
                startActivity(intent);
            }
        });

        reminder_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, WorkReminder.class);
                startActivity(intent);
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference().child("Global_value");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String recovered = dataSnapshot.child("recovered").getValue().toString();
                String confirmed = dataSnapshot.child("confirmed").getValue().toString();

                recovered_num.setText(recovered);
                confirmed_num.setText(confirmed);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
