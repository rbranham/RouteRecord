package com.branham.roger.routerecord.models;

import android.support.annotation.StringRes;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class Trip {

    //Job Type Variables //Currently not used
    public static final int REGULAR_ROUTE_JOB_TYPE = 0;
    public static final int AFTER_SCHOOL_JOB_TYPE = 1;
    public static final int ATHLETIC_TRIP_JOB_TYPE = 2;
    public static final int FIELD_TRIP_JOB_TYPE = 3;

    //Job Type Variables
    //TODO: Get type Variables from String resources files
    //String[] myArray = getResources().getStringArray(R.array.trip_type_array);

    private String tripName, jobType, date;
    private float hours, miles;
    private int  busNum;
    private boolean isCompleted;
    FirebaseUser driver;

    //TODO: store trip id after reads for easy identification

    //trip date?

    public Trip(String tripName) {
        this.tripName = tripName;
    }


    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public int getBusNum() {
        return busNum;
    }

    public void setBusNum(int busNum) {
        this.busNum = busNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public float getMiles() {
        return miles;
    }

    public void setMiles(float miles) {
        this.miles = miles;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
}
