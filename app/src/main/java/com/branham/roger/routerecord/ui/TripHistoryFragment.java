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
import com.branham.roger.routerecord.models.Trip;
import com.branham.roger.routerecord.adapters.FinishedTripAdapter;
import com.branham.roger.routerecord.viewmodels.TripHistoryViewModel;

import java.util.ArrayList;

public class TripHistoryFragment extends Fragment implements FinishedTripAdapter.OnTripListener {


    private static final String TAG = "TripHistoryFragment";

    //TODO: Only Displays the last one??

    //vars
    private View mView;
    RecyclerView mRecyclerView;
    FinishedTripAdapter mAdapter;
    private TripHistoryViewModel mTripHistoryViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_trip_history,container,false);
        //FragmentActivity c = getActivity();
        mRecyclerView = mView.findViewById(R.id.trip_history_recycler_view);

        mTripHistoryViewModel = ViewModelProviders.of(this).get(TripHistoryViewModel.class); //TODO: Should this be on overide method onAtivity Created to make onClick Work??

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
        mAdapter = new FinishedTripAdapter(mView.getContext(), mTripHistoryViewModel.getTrips().getValue(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onTripClick(int position) {
        //RecyclerView OnClickListener(Best Practice Way) - Coding with Mitch <-- Look here to see how to open an activity with this
        //TODO: Open an activity///Fragment??/// for trip detail view
        Toast.makeText(mView.getContext(), "Clicked:" + mTripHistoryViewModel.getTrips().getValue().get(position).getTripName(), Toast.LENGTH_SHORT).show();
    }

}
