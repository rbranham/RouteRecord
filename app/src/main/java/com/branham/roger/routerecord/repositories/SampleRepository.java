package com.branham.roger.routerecord.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.branham.roger.routerecord.models.Trip;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Singleton pattern
 */
public class SampleRepository {

    private static SampleRepository instance;
    private ArrayList<Trip> dataSet = new ArrayList<>();

    public static SampleRepository getInstance(){
        if(instance == null){
            instance = new SampleRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<Trip>> getTrips(){
        setSampleData(); //Pretend action to get data from webservice

        MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }


    private void setSampleData(){
        ArrayList<Trip> sampleList = new ArrayList<Trip>();

        Trip trip1 = new Trip();
        trip1.setTripName("Baseball To Mellen");
        trip1.setDate("5/25/19");
        trip1.setMiles(132);
        trip1.setHours(5.5f);

        Trip trip2 = new Trip();
        trip2.setTripName("Softball To Mellen");
        trip2.setDate("5/27/19");
        trip2.setMiles(140);
        trip2.setHours(3.5f);

        Trip trip3 = new Trip();
        trip3.setTripName("Baseball To Hurly");
        trip3.setDate("5/23/19");
        trip3.setMiles(160);
        trip3.setHours(5.6f);

        Trip trip4 = new Trip();
        trip4.setTripName("Track To Drummond");
        trip4.setDate("5/29/19");
        trip4.setMiles(70);
        trip4.setHours(6.7f);

        sampleList.add(trip1);
        sampleList.add(trip2);
        sampleList.add(trip3);
        sampleList.add(trip4);

        //return sampleList;
        dataSet = sampleList;
    }

}
