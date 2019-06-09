package com.branham.roger.routerecord.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.adapters.TripAdapter;
import com.branham.roger.routerecord.viewmodels.TripHistoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class TripHistoryFragment extends Fragment {


    private static final String TAG = "TripHistoryFragment";

    //TODO: Only Displays the last one??

    //vars
    private View mView;
    //ArrayList<Trip> mTrips;
    RecyclerView mRecyclerView;
    TripAdapter mAdapter;
    private TripHistoryViewModel mTripHistoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Sample Array List Population
        //mTrips = sampleData();

        mView = inflater.inflate(R.layout.fragment_trip_history,container,false);
        //FragmentActivity c = getActivity();
        mRecyclerView = mView.findViewById(R.id.trip_history_recycler_view);

        mTripHistoryViewModel = ViewModelProviders.of(this).get(TripHistoryViewModel.class);

        mTripHistoryViewModel.init();

        mTripHistoryViewModel.getTrips().observe(this, new Observer<ArrayList<Trip>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Trip> trips) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();

        return mView;
    }

    //TODO: Set on Clicks To open New TripInfoActivity to display full info

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recycleview.");
        mAdapter = new TripAdapter(mView.getContext(), mTripHistoryViewModel.getTrips().getValue());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }



}
