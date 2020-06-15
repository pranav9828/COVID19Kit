package com.corona.covid_19explorer.Model;

import com.covid.covid_19explorer.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<News> getNews(
            @Query("country") String country,
            @Query("q") String coronaVirus,
            @Query("apiKey") String apiKey
    );
}
