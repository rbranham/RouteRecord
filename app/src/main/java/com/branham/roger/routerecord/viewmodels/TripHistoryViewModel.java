package com.branham.roger.routerecord.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.repositories.FirebaseDbUtils;
import com.branham.roger.routerecord.repositories.SampleRepository;

import java.util.ArrayList;



public class TripHistoryViewModel extends ViewModel {

    private static final String TAG= "TripHistoryViewModel";

    private MutableLiveData<ArrayList<Trip>> mTrips;
    //private SampleRepository mRepo;


    public void init(){
        Log.d(TAG, "init: ");
        if(mTrips != null) {
            return;
        }
        //mRepo = SampleRepository.getInstance();
        //mTrips = mRepo.getTrips();
        mTrips = FirebaseDbUtils.getUserTrips(); //TODO: Change FirebaseDBUtils to singleton?
    }

    public LiveData<ArrayList<Trip>> getTrips(){
        return mTrips;
    }


}
