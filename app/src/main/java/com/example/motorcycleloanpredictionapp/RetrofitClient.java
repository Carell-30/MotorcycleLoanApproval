package com.example.motorcycleloanpredictionapp;

import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // Replace with the correct IP address of your Flask API
    private static final String BASE_URL = "http://192.168.1.89:5000";  // Ensure this is your Flask server's IP address
    private static Retrofit retrofit;

    // Singleton pattern to get a single Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // Base URL for the API
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().setLenient().create() // Optional: Use lenient parsing for JSON
                    ))
                    .build();
        }
        return retrofit;
    }
}
