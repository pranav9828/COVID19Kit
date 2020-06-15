package com.corona.covid_19explorer.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.corona.covid_19explorer.Activity.EmergencyContact;
import com.corona.covid_19explorer.R;

public class EmergencyFragment extends Fragment {

    TextView tv10, tv11;
    public static final int REQUEST_CALL = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emergency, container, false);
        tv10 = (TextView) view.findViewById(R.id.textView10);
        tv11 = (TextView) view.findViewById(R.id.textView11);

        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });

        return view;
    }

    private void makeCall() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String number="tel:"+1075;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(number)));
        }
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel: +911123978046")));
        }
    }
}
