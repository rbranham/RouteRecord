package com.branham.roger.routerecord.repositories;

import android.support.annotation.NonNull;
import android.util.Log;

import com.branham.roger.routerecord.models.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**This class contains static methods to work with the firebase database*/
public class FirebaseDbUtils {
    final static String TAG = "FirebaseDbUtils";

    ////FireBase Instance Variables

    //get database Instance
    static FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**Function to add trip to FireStore Db */
    public static void addTrip(Trip trip){

        Map<String, Object> newTrip = new HashMap<>();

        //code here to take trip values and put them into hash map
        newTrip.put(TripContract.tripDB.TRIP_NAME, trip.getTripName());
        newTrip.put(TripContract.tripDB.HOURS, trip.getHours());
        newTrip.put(TripContract.tripDB.MILES, trip.getMiles());
        newTrip.put(TripContract.tripDB.TRIP_TYPE, trip.getJobType());
        newTrip.put(TripContract.tripDB.DATE, trip.getDate());
        newTrip.put(TripContract.tripDB.BUS_NUMBER, trip.getBusNum());

        db.collection(TripContract.tripDB.COLLECTION_NAME)
                .add(newTrip)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



    /**Function to pull all jobs from user*/
    public static ArrayList<Trip> getUserTrips() {
        return null; //TODO: getUserTrips() stub
    }

    /**Function to pull all user's jobs from last two weeks*/  //TODO: should eventually change to caching last two weeks
    public static ArrayList<Trip> getUserTripsLastTwoWeeks(){
         return null; //TODO: getUserTripsLastTwoWeeks() stub
    }

    /**Function to pull all trips from all users in the last two weeks*/
    public static ArrayList<Trip> getLastTwoWeeks() { return null;} //TODO: getLastTwoWeeks() stub



}
