package com.corona.covid_19explorer.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.corona.covid_19explorer.Fragments.DonationsFragment;
import com.corona.covid_19explorer.Fragments.EmergencyFragment;
import com.corona.covid_19explorer.Fragments.HomeFragment;
import com.corona.covid_19explorer.Fragments.IdeasFragment;
import com.corona.covid_19explorer.Fragments.LocationFragment;
import com.corona.covid_19explorer.Fragments.NewsFragment;
import com.corona.covid_19explorer.Fragments.PreventFragment;
import com.corona.covid_19explorer.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportActionBar().setTitle("Home");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_donation:
                getSupportActionBar().setTitle("Donations");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DonationsFragment()).commit();
                break;
            case R.id.nav_prevention:
                getSupportActionBar().setTitle("Prevent COVID-19");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PreventFragment()).commit();
                break;
            case R.id.nav_ideas:
                getSupportActionBar().setTitle("Submit your Ideas");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new IdeasFragment()).commit();
                break;
            case R.id.nav_news:
                getSupportActionBar().setTitle("COVID-19 News");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit();
                break;
            case R.id.nav_emergency:
                getSupportActionBar().setTitle("Emergency");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EmergencyFragment()).commit();
                break;
            case R.id.nav_location:
                getSupportActionBar().setTitle("Share your Location");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LocationFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}