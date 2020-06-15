package com.corona.covid_19explorer.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.corona.covid_19explorer.Activity.Ideas;
import com.corona.covid_19explorer.Classes.AddIdeas;
import com.corona.covid_19explorer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IdeasFragment extends Fragment {

    TextView name,mobile,idea;
    Button submit;
    DatabaseReference dreff;
    AddIdeas addIdeas;
    long maxid=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ideas, container, false);
        name = (TextView) view.findViewById(R.id.name);
        mobile = (TextView) view.findViewById(R.id.phone_num);
        idea = (TextView) view.findViewById(R.id.idea);
        submit = (Button) view.findViewById(R.id.submit_btn);
        dreff = FirebaseDatabase.getInstance().getReference().child("Ideas");
        addIdeas = new AddIdeas();

        dreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().isEmpty() || mobile.getText().toString().trim().isEmpty() || idea.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(),"Fields can't be empty.", Toast.LENGTH_SHORT).show();
                }
                else if (mobile.length() != 10){
                    mobile.setError("Mobile number should be 10 digits.");
                    mobile.setFocusable(true);
                }
                else if (name.getText().toString().matches("(.*[0-9].*)") || name.getText().toString().matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)")){
                    name.setError("Name field is invalid.");
                    name.setFocusable(true);
                }
                else if (idea.getText().toString().matches("(.*[0-9].*)") || idea.getText().toString().matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)")){
                    idea.setError("Idea field is invalid.");
                    idea.setFocusable(true);
                }
                else if (mobile.getText().toString().matches("(.*[A-Z].*)") || mobile.getText().toString().matches("(.*[a-z].*)") || mobile.getText().toString().matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)")){
                    mobile.setError("Mobile field should contain only numeric Characters");
                    mobile.setFocusable(true);
                }
                else {
                    addIdeas.setName(name.getText().toString().trim());
                    addIdeas.setMobile(mobile.getText().toString().trim());
                    addIdeas.setIdea(idea.getText().toString().trim());
                    dreff.child(String.valueOf(maxid+1)).setValue(addIdeas);
                    Toast.makeText(getContext(),"Idea submitted successfully",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    mobile.setText("");
                    idea.setText("");
                }
            }
        });
        return view;
    }
}
