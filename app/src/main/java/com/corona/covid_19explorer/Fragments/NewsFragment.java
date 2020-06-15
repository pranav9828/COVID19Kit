package com.corona.covid_19explorer.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.corona.covid_19explorer.Activity.CoronaNews;
import com.corona.covid_19explorer.Adapter.Adapter;
import com.corona.covid_19explorer.Adapter.NewsAdapter;
import com.corona.covid_19explorer.Classes.Headlines;
import com.corona.covid_19explorer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    List<Headlines> headline;
    NewsAdapter adapter;
    ProgressDialog pd;
    String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        headline = new ArrayList<>();
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading...");
        pd.show();
        jsonRequest();
        return view;
    }

    private void jsonRequest() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray = response.optJSONArray("articles");
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        Headlines headlines = new Headlines();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        headlines.setAuthor(jsonObject.getString("author"));
                        headlines.setTitle(jsonObject.getString("title"));
                        headlines.setDescription(jsonObject.getString("description"));
                        headlines.setUrl(jsonObject.getString("url"));
                        headlines.setUrlToImage(jsonObject.getString("urlToImage"));
                        headlines.setPublishedAt(jsonObject.getString("publishedAt"));
                        headline.add(headlines);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    pd.dismiss();
                    adapter = new NewsAdapter(getContext(), headline);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onRefresh() {

    }
}
