package com.example.covid_19.models;

public class Regional {

    String location;
    Integer confirmed_indian_case;
    Integer confirmed_foreigner_case;
    Integer dischared;
    Integer deaths;

    public Regional() {
    }

    public Regional(String location, Integer confirmed_indian_case, Integer confirmed_foreigner_case, Integer dischared, Integer deaths) {
        this.location = location;
        this.confirmed_indian_case = confirmed_indian_case;
        this.confirmed_foreigner_case = confirmed_foreigner_case;
        this.dischared = dischared;
        this.deaths = deaths;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getConfirmed_indian_case() {
        return confirmed_indian_case;
    }

    public void setConfirmed_indian_case(Integer confirmed_indian_case) {
        this.confirmed_indian_case = confirmed_indian_case;
    }

    public Integer getConfirmed_foreigner_case() {
        return confirmed_foreigner_case;
    }

    public void setConfirmed_foreigner_case(Integer confirmed_foreigner_case) {
        this.confirmed_foreigner_case = confirmed_foreigner_case;
    }

    public Integer getDischared() {
        return dischared;
    }

    public void setDischared(Integer dischared) {
        this.dischared = dischared;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }
}
