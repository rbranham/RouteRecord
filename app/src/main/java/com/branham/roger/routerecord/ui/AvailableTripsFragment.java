package com.branham.roger.routerecord.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.adapters.FinishedTripAdapter;
import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.viewmodels.AvailableTripsViewModel;
import com.branham.roger.routerecord.viewmodels.TripHistoryViewModel;

import java.util.ArrayList;

public class AvailableTripsFragment extends Fragment implements FinishedTripAdapter.OnTripListener{

    private static final String TAG = "AvailableTripsFragment";

    private AvailableTripsViewModel mViewModel;

    //vars
    private View mView;
    RecyclerView mRecyclerView;
    FinishedTripAdapter mAdapter;

    public static AvailableTripsFragment newInstance() {
        return new AvailableTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.available_trips_fragment, container, false);

        mRecyclerView = mView.findViewById(R.id.available_trips_recycler_view);

        mViewModel = ViewModelProviders.of(this).get(AvailableTripsViewModel.class);

        mViewModel.init();

        mViewModel.getTrips().observe(this, new Observer<ArrayList<Trip>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Trip> trips) {
                mAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();

        return mView;
    }

    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AvailableTripsViewModel.class);
        // TODO: Use the ViewModel
        initRecyclerView();
    }
    */

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recycler view.");
        mAdapter = new FinishedTripAdapter(mView.getContext(), mViewModel.getTrips().getValue(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTripClick(int position) {
        //TODO: Prompt Dialog to accept trip
        Toast.makeText(mView.getContext(), "Clicked:" + mViewModel.getTrips().getValue().get(position).getTripName(), Toast.LENGTH_SHORT).show();
    }
}
