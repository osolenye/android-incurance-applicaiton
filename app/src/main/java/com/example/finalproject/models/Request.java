package com.example.finalproject.models;

import android.net.Uri;

import java.io.File;

public class Request {
    int policy;
    int summ;
    File file;

    public Request(int policy, int summ, File uri) {
        this.policy = policy;
        this.summ = summ;
        this.file = uri;
    }
}
