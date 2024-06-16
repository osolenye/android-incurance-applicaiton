package com.example.finalproject.models;

public class DmsPolicy {
    String start_date;
    String end_date;
    String industry;

    public DmsPolicy(String start_date, String end_date) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.industry = "Медицина";
    }
}
