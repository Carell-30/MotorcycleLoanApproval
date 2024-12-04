package com.example.motorcycleloanpredictionapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MotorAPI {
    @POST("predict")  // Ensure this matches your Flask route (e.g., /predict)
    Call<MotorcycleLoanPredictionResponse> getMotorcycleLoanPrediction(@Body MotorcycleLoanRequest data);
}
