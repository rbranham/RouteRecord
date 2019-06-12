package com.branham.roger.routerecord.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.branham.roger.routerecord.models.Trip;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**This class contains static methods to work with the firebase database*/
public class FirebaseDbUtils {
    final static String TAG = "FirebaseDbUtils";

    ////FireBase Instance Variables

    //get database Instance



    /**Function to add trip to FireStore Db */
    public static void addTrip(Trip trip){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final DocumentReference newTripRef =
                db.collection(TripContract.tripDB.COLLECTION_NAME)
                .document();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        trip.setCreator(userID);
        trip.setTime_created(null);

        newTripRef.set(trip).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "DocumentSnapshot added with ID: " + newTripRef.getId());
                    //TODO: Some type of Conformation to user that data was added succesfully, Maybe SnackBar Message?
                }else {

                }
            }
        });

    }



    /**Function to pull all jobs from user*/
    public static MutableLiveData<ArrayList<Trip>> getUserTrips() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d(TAG, "getUserTrips: called");
        final ArrayList<Trip> dataSet = new ArrayList<>();
        final MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();

        db.collection(TripContract.tripDB.COLLECTION_NAME) //TODO: Not getting run??
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG,  document.getId() + " => " + document.getData());
                                Trip tempTrip = document.toObject(Trip.class);
                                dataSet.add(tempTrip);

                            }
                        } else {
                            Log.d(TAG, "getUserTrips: Error getting documents: ", task.getException());
                        }

                        data.setValue(dataSet); //Solved My issues
                    }
                });

        data.setValue(dataSet); //Initial

        return data;
    }

    /**Function to pull all user's jobs from last two weeks*/  //TODO: should eventually change to caching last two weeks
    public static ArrayList<Trip> getUserTripsLastTwoWeeks(){
         return null; //TODO: getUserTripsLastTwoWeeks() stub
    }

    /**Function to pull all trips from all users in the last two weeks*/
    public static ArrayList<Trip> getLastTwoWeeks() { return null;} //TODO: getLastTwoWeeks() stub


    /**Function to pull all trips from db*/ //Just used for testing  //TODO: Not Completed
    public static MutableLiveData<ArrayList<Trip>> getAllTrips() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Log.d(TAG, "getAllTrips: called");
        final ArrayList<Trip> dataSet = new ArrayList<>();
        final MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();

        db.collection(TripContract.tripDB.COLLECTION_NAME) //TODO: Not getting run??
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG,  document.getId() + " => " + document.getData());
                                    Trip tempTrip = document.toObject(Trip.class);
                                    dataSet.add(tempTrip);

                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }

                        data.setValue(dataSet); //Solved My issues
                    }
                });

        data.setValue(dataSet); //Initial

        return data;
    }

}
