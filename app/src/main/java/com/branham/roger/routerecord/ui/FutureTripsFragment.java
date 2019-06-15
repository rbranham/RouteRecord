package com.branham.roger.routerecord.ui;

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
import com.branham.roger.routerecord.viewmodels.FutureTripsViewModel;

public class FutureTripsFragment extends Fragment implements FinishedTripAdapter.OnTripListener{

    private static final String TAG = "FutureTripsFragment";

    private FutureTripsViewModel mViewModel;

    //vars
    private View mView;
    RecyclerView mRecyclerView;
    FinishedTripAdapter mAdapter;

    public static FutureTripsFragment newInstance() {
        return new FutureTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.future_trips_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FutureTripsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ");
        //TODO: Set up RecyclerView

    }

    @Override
    public void onTripClick(int position) {
        //TODO: Prompt Dialog to add Trip or Remove trip
        Toast.makeText(mView.getContext(), "Clicked:" + mViewModel.getTrips().getValue().get(position).getTripName(), Toast.LENGTH_SHORT).show();
    }
}
