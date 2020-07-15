package com.corona.covid_19explorer;

public class Country {
    private String name;
    private long total, newCases, totalDeaths, newDeaths, totalRecovered, activeCases, seriousCases;

    public Country() {}

    public Country(String name, long total, long newCases, long totalDeaths, long newDeaths, long totalRecovered, long activeCases, long seriousCases) {
        this.name = name;
        this.total = total;
        this.newCases = newCases;
        this.totalDeaths = totalDeaths;
        this.newDeaths = newDeaths;
        this.totalRecovered = totalRecovered;
        this.activeCases = activeCases;
        this.seriousCases = seriousCases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getNewCases() {
        return newCases;
    }

    public void setNewCases(long newCases) {
        this.newCases = newCases;
    }

    public long getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(long totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public long getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(long newDeaths) {
        this.newDeaths = newDeaths;
    }

    public long getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(long totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public long getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(long activeCases) {
        this.activeCases = activeCases;
    }

    public long getSeriousCases() {
        return seriousCases;
    }

    public void setSeriousCases(long seriousCases) {
        this.seriousCases = seriousCases;
    }
}