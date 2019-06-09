package com.branham.roger.routerecord.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.repositories.SampleRepository;

import java.util.ArrayList;

public class TripHistoryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Trip>> mTrips;
    private SampleRepository mRepo;

    public void init(){
        if(mTrips != null) {
            return;
        }
        mRepo = SampleRepository.getInstance();
        mTrips = mRepo.getTrips();
    }

    public LiveData<ArrayList<Trip>> getTrips(){
        return mTrips;
    }


}
