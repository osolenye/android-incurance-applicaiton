package com.example.finalproject.models;

import com.google.gson.annotations.SerializedName;

public class Token {
    @SerializedName("access")
    private String accessToken;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
