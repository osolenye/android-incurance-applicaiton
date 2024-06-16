package com.example.finalproject.models;

public class AccidentPolicy {
    private String start_date;
    private String end_date;
    private String industry;
    private String travel_country;


    public AccidentPolicy(String start_date, String end_date, String travel_country) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.industry = "Accident";
        this.travel_country = travel_country;
    }
}
