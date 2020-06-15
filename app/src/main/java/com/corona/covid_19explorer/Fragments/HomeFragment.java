package com.corona.covid_19explorer.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.corona.covid_19explorer.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    TextView recoveredNumber, confirmedNumber, deathNumber, activeNumber;
    PieChart pieChart;
    ValueLineChart valueLineChart;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recoveredNumber = (TextView) view.findViewById(R.id.recovered_num);
        confirmedNumber = (TextView) view.findViewById(R.id.confirmed_num);
        deathNumber = (TextView) view.findViewById(R.id.death_num);
        activeNumber = (TextView) view.findViewById(R.id.active_num);

        pieChart = view.findViewById(R.id.pieChart);
        valueLineChart = view.findViewById(R.id.lineChart);

        fetchData();
        return view;
    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    recoveredNumber.setText(jsonObject.getString("recovered"));
                    confirmedNumber.setText(jsonObject.getString("cases"));
                    deathNumber.setText(jsonObject.getString("deaths"));
                    activeNumber.setText(jsonObject.getString("active"));

                    pieChart.addPieSlice(new PieModel("recovered", Integer.parseInt(recoveredNumber.getText().toString()), Color.BLUE));
                    pieChart.addPieSlice(new PieModel("cases", Integer.parseInt(confirmedNumber.getText().toString()), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("deaths", Integer.parseInt(deathNumber.getText().toString()), Color.BLACK));
                    pieChart.addPieSlice(new PieModel("active", Integer.parseInt(activeNumber.getText().toString()), Color.RED));
                    pieChart.startAnimation();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
