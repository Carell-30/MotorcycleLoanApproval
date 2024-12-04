package com.example.motorcycleloanpredictionapp;

import com.google.gson.annotations.SerializedName;

public class MotorcycleLoanRequest {
    @SerializedName("Applicant Income")
    private double applicantIncome;

    @SerializedName("Credit Score")
    private int creditScore;

    @SerializedName("Loan Amount")
    private double motorcycleLoanAmount;

    @SerializedName("Loan Term")
    private int loanTerm;

    // Constructor
    public MotorcycleLoanRequest(double applicantIncome, int creditScore, double motorcycleLoanAmount, int loanTerm) {
        this.applicantIncome = applicantIncome;
        this.creditScore = creditScore;
        this.motorcycleLoanAmount = motorcycleLoanAmount;
        this.loanTerm = loanTerm;
    }

    // Getters and Setters
    public double getApplicantIncome() {
        return applicantIncome;
    }

    public void setApplicantIncome(double applicantIncome) {
        this.applicantIncome = applicantIncome;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public double getMotorcycleLoanAmount() {
        return motorcycleLoanAmount;
    }

    public void setMotorcycleLoanAmount(double motorcycleLoanAmount) {
        this.motorcycleLoanAmount = motorcycleLoanAmount;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }
}
