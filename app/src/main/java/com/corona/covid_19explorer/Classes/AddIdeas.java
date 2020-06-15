package com.corona.covid_19explorer.Classes;

public class AddIdeas {

    public String name,mobile,idea;

    public AddIdeas() {
    }

    public AddIdeas(String name, String mobile, String idea) {
        this.name = name;
        this.mobile = mobile;
        this.idea = idea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }
}
