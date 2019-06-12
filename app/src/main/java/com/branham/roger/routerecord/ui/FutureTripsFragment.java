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
import com.branham.roger.routerecord.viewmodels.FutureTripsViewModel;

public class FutureTripsFragment extends Fragment {

    private static final String TAG = "FutureTripsFragment";

    private FutureTripsViewModel mViewModel;

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
}
