package com.example.finalproject.models;

public class Policy {
    private final String industry; // required
    private final String name; // nullable
    private final Integer price; // nullable
    private final String travel_country; // nullable
    private final String auto_num; // nullable
    private final String auto_model; // nullable
    private final String auto_mark; // nullable
    private final String car_release; // nullable
    private final String start_date; // nullable
    private final String end_date; // nullable
    private final String user; // nullable

    // Private constructor to enforce the use of the Builder
    private Policy(Builder builder) {
        this.industry = builder.industry;
        this.name = builder.name;
        this.price = builder.price;
        this.travel_country = builder.travel_country;
        this.auto_num = builder.auto_num;
        this.auto_model = builder.auto_model;
        this.auto_mark = builder.auto_mark;
        this.car_release = builder.car_release;
        this.start_date = builder.start_date;
        this.end_date = builder.end_date;
        this.user = builder.user;
    }

    // Static inner Builder class
    public static class Builder {
        private final String industry; // required
        private String name; // nullable
        private Integer price; // nullable
        private String travel_country; // nullable
        private String auto_num; // nullable
        private String auto_model; // nullable
        private String auto_mark; // nullable
        private String car_release; // nullable
        private String start_date; // nullable
        private String end_date; // nullable
        private String user; // nullable

        public Builder(String industry) {
            this.industry = industry;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder travel_country(String travel_country) {
            this.travel_country = travel_country;
            return this;
        }

        public Builder auto_num(String auto_num) {
            this.auto_num = auto_num;
            return this;
        }

        public Builder auto_model(String auto_model) {
            this.auto_model = auto_model;
            return this;
        }

        public Builder auto_mark(String auto_mark) {
            this.auto_mark = auto_mark;
            return this;
        }

        public Builder car_release(String car_release) {
            this.car_release = car_release;
            return this;
        }

        public Builder start_date(String start_date) {
            this.start_date = start_date;
            return this;
        }

        public Builder end_date(String end_date) {
            this.end_date = end_date;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Policy build() {
            return new Policy(this);
        }
    }
}

