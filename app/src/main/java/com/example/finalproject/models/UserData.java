package com.example.finalproject.models;

import java.io.Serializable;
import java.util.HashMap;

public class UserData implements Serializable {
    private HashMap<String, String> userData;

    public UserData(HashMap<String, String> userData) {
        this.userData = userData;
    }

    public HashMap<String, String> getUserData() {
        return userData;
    }
}