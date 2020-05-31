package com.covid.covid_19explorer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResult")
    @Expose
    private String totalResult;

    @SerializedName("articles")
    @Expose
    private List<com.corona.covid_19explorer.Model.Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResult;
    }

    public void setTotalResults(String totalResult) {
        this.totalResult = totalResult;
    }

    public List<com.corona.covid_19explorer.Model.Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<com.corona.covid_19explorer.Model.Articles> articles) {
        this.articles = articles;
    }
}
