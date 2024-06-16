package com.example.finalproject.models;

public class AutoPolicy {
    String auto_mark;
    String auto_model;
    String car_release;
    String start_date;
    String end_date;
    String travel_contry;
    String industry;

    public AutoPolicy(String auto_mark, String auto_model, String car_release, String start_date, String end_date, String travel_contry) {
        this.auto_mark = auto_mark;
        this.auto_model = auto_model;
        this.car_release = car_release;
        this.start_date = start_date;
        this.end_date = end_date;
        this.travel_contry = travel_contry;
        this.industry = "ОСАГО";
    }
}
