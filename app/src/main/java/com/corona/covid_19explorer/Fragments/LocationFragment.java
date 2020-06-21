package com.corona.covid_19explorer.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.corona.covid_19explorer.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationFragment extends Fragment {

    TextView location;
    Button getLocation, shareLocation;
    Geocoder geocoder;
    List<Address> addresses;
    private static final int REQUEST_INT = 101;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_location, container, false);
        location = view.findViewById(R.id.location);
        getLocation = view.findViewById(R.id.getLocation);
        shareLocation = view.findViewById(R.id.shareLocation);
        geocoder = new Geocoder(getContext(), Locale.getDefault());

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_INT);
                } else {
                    getCurrentLocation();
                }
            }
        });

        shareLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Please get location.", Toast.LENGTH_SHORT).show();
                }
                else {
                    final LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(10000);
                    locationRequest.setFastestInterval(3000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_INT);
                        return;
                    }
                    LocationServices.getFusedLocationProviderClient(getContext()).requestLocationUpdates(locationRequest, new LocationCallback(){

                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            LocationServices.getFusedLocationProviderClient(getContext())
                                    .removeLocationUpdates(this);
                            if (locationResult != null && locationResult.getLocations().size() > 0){
                                int latestLocationIndex = locationResult.getLocations().size() - 1;
                                double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                                double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                try {
                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                    String address = addresses.get(0).getAddressLine(0);
                                    String area = addresses.get(0).getLocality();
                                    String city = addresses.get(0).getAdminArea();
                                    String country = addresses.get(0).getCountryName();
                                    String postalcode = addresses.get(0).getPostalCode();

                                    String full_address = "Quarantine Location :" + "\n" + address + "," + area + "," + city + "," + country + "," + postalcode;
                                    intent.putExtra(android.content.Intent.EXTRA_TEXT, full_address);
                                    startActivity(intent.createChooser(intent, "Share via"));

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
//                    location.setText("Latitude = " + latitude + "\nLongitude = " + longitude);
                            }
                        }
                    }, Looper.myLooper());
                }
            }
        });
        return view;
    }

    private void getCurrentLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_INT);
            return;
        }
        LocationServices.getFusedLocationProviderClient(getContext()).requestLocationUpdates(locationRequest, new LocationCallback(){

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(getContext())
                        .removeLocationUpdates(this);
                if (locationResult != null && locationResult.getLocations().size() > 0){
                    int latestLocationIndex = locationResult.getLocations().size() - 1;
                    double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                    double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                    try {
                        addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        String address = addresses.get(0).getAddressLine(0);
                        String area = addresses.get(0).getLocality();
                        String city = addresses.get(0).getAdminArea();
                        String country = addresses.get(0).getCountryName();
                        String postalcode = addresses.get(0).getPostalCode();
                        StringBuilder builder = new StringBuilder();
                        builder.append(address).append("\n " + area).append("\n " + city).append("\n " + country).append("\n " + postalcode);
                        String res = builder.toString();
                        location.setText(res);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    location.setText("Latitude = " + latitude + "\nLongitude = " + longitude);
                }
            }
        }, Looper.myLooper());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_INT && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
            else {
                Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
