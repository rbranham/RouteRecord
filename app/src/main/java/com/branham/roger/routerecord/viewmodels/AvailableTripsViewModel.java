package com.branham.roger.routerecord.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.repositories.FirebaseDbUtils;

import java.util.ArrayList;

public class AvailableTripsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private static final String TAG = "AvailableTripsViewModel";

    private MutableLiveData<ArrayList<Trip>> mTrips;

    public void init(){
        Log.d(TAG, "init: ");
        if(mTrips != null) {
            return;
        }

        mTrips = FirebaseDbUtils.getAvailableTrips();
    }

    public LiveData<ArrayList<Trip>> getTrips(){
        return mTrips;
    }
}
