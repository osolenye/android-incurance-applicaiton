package com.example.finalproject.network;

import com.example.finalproject.models.AccidentPolicy;
import com.example.finalproject.models.AutoPolicy;
import com.example.finalproject.models.CargoPolicy;
import com.example.finalproject.models.DmsPolicy;
import com.example.finalproject.models.Login;
import com.example.finalproject.models.Person;
import com.example.finalproject.models.Policy;
import com.example.finalproject.models.Request;
import com.example.finalproject.models.Token;
import com.example.finalproject.models.TravelPolicy;
import com.example.finalproject.models.YurUser;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("register/")
    Call<Void> sendYurUser(@Body YurUser yurUser);

    @POST("register/")
    Call<Void> sendPerson(@Body Person person);

    @POST("token/")
    Call<Token> sendAuth(@Body Login login);

    @GET("profile/")
    Call<Map<String, Object>> getProfile(@Header("Authorization") String token);


    @POST("accident_policies/")
    Call<Void> accidentPolicies(@Header("Authorization") String token, @Body AccidentPolicy accidentPolicy);

    @POST("auto_policies/")
    Call<Void> autoPolicies(@Header("Authorization") String token, @Body AutoPolicy autoPolicy);



    @POST("health_policies/")
    Call<Void> health_policies(@Header("Authorization") String token, @Body DmsPolicy dmsPolicy);

    @POST("cargo_policies/")
    Call<Void> cargoPolicies(@Header("Authorization") String token, @Body CargoPolicy cargoPolicy);


    @POST("travel_policies/")
    Call<Void> travelPolicies(@Header("Authorization") String token, @Body TravelPolicy travelPolicy);


    @POST("health_payments/")
    Call<Void> healthPayments(@Header("Authorization") String token, @Body Request request);
}
