package com.example.finalproject.network;

import com.example.finalproject.models.Login;
import com.example.finalproject.models.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/endpoint")
    Call<Token> sendPostRequest(@Body Login login);
}
