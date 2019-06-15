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
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
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



    /**Function to add already completed trip to FireStore Db */
    public static void addNewCompletedTrip(Trip trip) {

        //This add trip only works for when driver adds own trip
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        trip.setCreator(userID);
        trip.setDriver(userID); //Driver and Creator are the same
        trip.setTime_created(null);

        addTrip(trip);

    }

    /**Function to add uncompleted trip to database*/
    public static void addAvailibleTrip(Trip trip) {

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        trip.setCreator(userID);
        trip.setDriver(null); //No Driver yet
        trip.setTime_created(null);

        addTrip(trip);
    }

    private static void addTrip(Trip trip){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setSettings(db);

        final DocumentReference newTripRef =
                db.collection(TripContract.tripDB.COLLECTION_NAME)
                        .document();

        newTripRef.set(trip).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "DocumentSnapshot added with ID: " + newTripRef.getId());
                    //TODO: Some type of Conformation to user that data was added successfully, Maybe SnackBar Message?
                } else {

                }
            }
        });
    }

    /**Function to pull all jobs from user only*/
    public static MutableLiveData<ArrayList<Trip>> getUserTrips() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setSettings(db);

        Log.d(TAG, "getUserTrips: called");
        final ArrayList<Trip> dataSet = new ArrayList<>();
        final MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();

        db.collection(TripContract.tripDB.COLLECTION_NAME)
                .whereEqualTo("driver", FirebaseAuth.getInstance().getCurrentUser().getUid() ) //TODO: make fields acseciable from one place, either string resourse or constants in class
                .orderBy("date", Query.Direction.DESCENDING)
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

                        data.setValue(dataSet);
                    }
                });

        data.setValue(dataSet); //Initially gets returned empty until live data get updated and finished

        return data;
    }

    /**Function to pull user's last 20 jobs from*/  //TODO: should eventually change to caching last two weeks
    public static ArrayList<Trip> getUserTripsLastTwentyInstances(){
        //Sample code from Firebase Docs
        //citiesRef.orderBy("name", Direction.DESCENDING).limit(3);

         return null; //TODO: getUserTripsLastTwoWeeks() stub
    }

    /**Function to pull all trips from all users in the last two weeks*/
    public static ArrayList<Trip> getLastTwoWeeks() { return null;} //TODO: getLastTwoWeeks() stub


    /**Function to pull all trips from db*/ //Just used for testing  //TODO: Not Completed
    public static MutableLiveData<ArrayList<Trip>> getAllTrips() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setSettings(db);

        Log.d(TAG, "getAllTrips: called");
        final ArrayList<Trip> dataSet = new ArrayList<>();
        final MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();

        db.collection(TripContract.tripDB.COLLECTION_NAME)
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

    public static MutableLiveData<ArrayList<Trip>> getAvailableTrips(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setSettings(db);

        Log.d(TAG, "getUserTrips: called");
        final ArrayList<Trip> dataSet = new ArrayList<>();
        final MutableLiveData<ArrayList<Trip>> data = new MutableLiveData<>();

        db.collection(TripContract.tripDB.COLLECTION_NAME)
                .whereEqualTo("available", true ) //TODO: make fields assessable from one place, either string resourse or constants in class
                .orderBy("date", Query.Direction.DESCENDING)
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

                        data.setValue(dataSet);
                    }
                });

        data.setValue(dataSet); //Initially gets returned empty until live data get updated and finished

        return data;
    }

    /**
     * Quick method to set settings of FireStore to cache data
     *  TODO: There has to be a better way to do this rather than calling everytime an instance of the db is called... Maybe on Sign in??
     *  TODO: I don't think this is working anyways
     * @param db
     */
    static public void setSettings(FirebaseFirestore db){
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);
    }
}
