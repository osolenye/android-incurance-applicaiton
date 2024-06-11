package com.example.finalproject.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//public class RetrofitClient {
//    private static final String BASE_URL = "http://95.87.93.126/api/";
//    private static Retrofit retrofit;
//
//    public static Retrofit getClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//}
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://95.87.93.126/api/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}