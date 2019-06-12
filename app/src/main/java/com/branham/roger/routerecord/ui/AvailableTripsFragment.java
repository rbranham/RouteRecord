package com.branham.roger.routerecord.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.viewmodels.AvailableTripsViewModel;

public class AvailableTripsFragment extends Fragment {

    private static final String TAG = "AvailableTripsFragment";

    private AvailableTripsViewModel mViewModel;

    public static AvailableTripsFragment newInstance() {
        return new AvailableTripsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.available_trips_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AvailableTripsViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: ");
        //TODO: Set up RecyclerView
    }

}
