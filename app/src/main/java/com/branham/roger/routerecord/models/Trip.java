package com.branham.roger.routerecord.models;


import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;


@IgnoreExtraProperties
public class Trip{

    //Job Type Variables //Currently not used
    public static final int REGULAR_ROUTE_JOB_TYPE = 0;
    public static final int AFTER_SCHOOL_JOB_TYPE = 1;
    public static final int ATHLETIC_TRIP_JOB_TYPE = 2;
    public static final int FIELD_TRIP_JOB_TYPE = 3;

    //Job Type Variables
    //TODO: Get type Variables from String resources files
    //String[] myArray = getResources().getStringArray(R.array.trip_type_array);



    private String trip_name;
    private String job_type;
    private String creator;
    private String driver;
    private String date;
    private @ServerTimestamp Date time_created;
    private float hours;
    private float miles;
    private int bus_num;

    //TODO: Test new booleans
    private boolean isCompleted;
    private boolean isAvailable;

/*
    private String name;
    private String job_type;
    private String creator;
    private @ServerTimestamp Date time_created;
    private String date;
    private int bus_num;
*/


    public Trip(String trip_name, String job_type, String date, Date time_created, float hours, float miles, int bus_num, boolean isCompleted, boolean isAvailable){
        this.trip_name = trip_name;
        this.job_type = job_type;
        this.date = date;
        this.time_created = time_created;
        this.hours = hours;
        this.miles = miles;
        this.bus_num = bus_num;
        this.isCompleted = isCompleted;
        this.isAvailable = isAvailable;
    }

    public Trip(){
        //Empty trip constructor
    }

    /*
    public Trip(String trip_name) {
        this.trip_name = trip_name;
    }
    */

    public String getTripName() {
        return trip_name;
    }

    public void setTripName(String trip_name) {
        this.trip_name = trip_name;
    }

    public int getBusNum() {
        return bus_num;
    }

    public void setBusNum(int bus_num) {
        this.bus_num = bus_num;
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
        return job_type;
    }

    public void setJobType(String job_type) {
        this.job_type = job_type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Date getTime_created() {
        return time_created;
    }

    public void setTime_created(Date time_created) {
        this.time_created = time_created;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
