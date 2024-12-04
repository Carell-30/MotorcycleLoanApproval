package com.example.motorcycleloanpredictionapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText etApplicantIncome, etCreditScore, etMotorcycleLoanAmount, etLoanTerm;
    private Button btnPredict;
    private TextView tvLoanPredictionResult;  // Reference for the prediction result TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        etApplicantIncome = findViewById(R.id.etApplicantIncome);
        etCreditScore = findViewById(R.id.etCreditScore);
        etMotorcycleLoanAmount = findViewById(R.id.etLoanAmount);
        etLoanTerm = findViewById(R.id.etLoanTerm);
        btnPredict = findViewById(R.id.btnPredict);
        tvLoanPredictionResult = findViewById(R.id.tvLoanPredictionResult);  // Initialize the result TextView

        // Initialize Retrofit using the RetrofitClient
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        MotorAPI api = retrofit.create(MotorAPI.class);

        // Set onClickListener for the Predict button
        btnPredict.setOnClickListener(v -> {
            // Get data from input fields
            String applicantIncome = etApplicantIncome.getText().toString();
            String creditScore = etCreditScore.getText().toString();
            String motorcycleLoanAmount = etMotorcycleLoanAmount.getText().toString();
            String loanTerm = etLoanTerm.getText().toString();

            // Validate input fields
            if (applicantIncome.isEmpty() || creditScore.isEmpty() || motorcycleLoanAmount.isEmpty() || loanTerm.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Parse numeric fields for additional validation
                double income = Double.parseDouble(applicantIncome);
                int credit = Integer.parseInt(creditScore);
                double amount = Double.parseDouble(motorcycleLoanAmount);
                int term = Integer.parseInt(loanTerm);

                if (income <= 0 || credit <= 0 || amount <= 0 || term <= 0) {
                    Toast.makeText(MainActivity.this, "Invalid input values", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create data object with parsed values
                MotorcycleLoanRequest data = new MotorcycleLoanRequest(income, credit, amount, term);

                // Make the API call
                Call<MotorcycleLoanPredictionResponse> call = api.getMotorcycleLoanPrediction(data);

                call.enqueue(new Callback<MotorcycleLoanPredictionResponse>() {
                    @Override
                    public void onResponse(Call<MotorcycleLoanPredictionResponse> call, Response<MotorcycleLoanPredictionResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String result = response.body().getPrediction();
                            // Set the result to the TextView instead of Toast
                            tvLoanPredictionResult.setText("Loan Status: " + result);
                        } else {
                            try {
                                String errorBody = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                                Log.e(TAG, "Error response: " + errorBody);
                                Toast.makeText(MainActivity.this, "Failed to get prediction: " + errorBody, Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Log.e(TAG, "Failed to parse error response", e);
                                Toast.makeText(MainActivity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MotorcycleLoanPredictionResponse> call, Throwable t) {
                        Log.e(TAG, "API Call Failed", t);
                        Toast.makeText(MainActivity.this, "API Call Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter valid numeric values", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
