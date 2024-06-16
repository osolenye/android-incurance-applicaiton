package com.example.finalproject.models;

public class CargoPolicy {
    String start_date;
    String end_date;
    String industry;
    String travel_country;


    public CargoPolicy(String start_date, String end_date, String travel_country) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.travel_country = travel_country;
        this.industry = "Грузы";
    }
}
