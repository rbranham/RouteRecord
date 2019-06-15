package com.branham.roger.routerecord.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.models.Trip;

import java.util.ArrayList;

public class FinishedTripAdapter extends RecyclerView.Adapter<FinishedTripAdapter.ViewHolder> {

    private static final String TAG = "FinishedTripAdapter";

    private ArrayList<Trip> mTrips = new ArrayList<>();
    private Context mContext;
    private OnTripListener mOnTripListener;

    public FinishedTripAdapter(Context context, ArrayList<Trip> tripList, OnTripListener onTripListener){
        mTrips = tripList;
        mContext = context;
        this.mOnTripListener = onTripListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_list_item_view, viewGroup, false);
        ViewHolder holder = new ViewHolder(view, mOnTripListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //TODO
        Log.d(TAG, "OnBindViewHolder: called.");

        //TODO: Change so that it can work for unCompleted jobs aswell
        if(mTrips.get(i).isCompleted()) {
            viewHolder.date.setText(mTrips.get(i).getDate());
            viewHolder.hours.setText(Float.toString(mTrips.get(i).getHours()) + " Hours");
            viewHolder.tripName.setText(mTrips.get(i).getTripName());
            viewHolder.miles.setText(Float.toString(mTrips.get(i).getMiles()) + " Miles");
        } else if(mTrips.get(i).isAvailable()){ //TODO: not done
            //Code for displaying unassigned trips
            viewHolder.tripName.setText(mTrips.get(i).getTripName());
            viewHolder.date.setText(mTrips.get(i).getDate());
            viewHolder.hours.setText("Start Time: " + mTrips.get(i).getStart_time());
            viewHolder.miles.setText(""); //This text box stays empty
        } else { //TODO: not done
            //Code for displaying assigned but uncompleted trips
            viewHolder.date.setText(mTrips.get(i).getDate());
            viewHolder.hours.setText(Float.toString(mTrips.get(i).getHours()));
            viewHolder.tripName.setText(mTrips.get(i).getTripName());
            viewHolder.miles.setText(Float.toString(mTrips.get(i).getMiles()));
        }

    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tripName, date, hours, miles;
        //RelativeLayout parentLayout;
        OnTripListener onTripListener;

        public ViewHolder(@NonNull View itemView, OnTripListener onTripListener) {
            super(itemView);


            tripName = itemView.findViewById(R.id.name_view);
            date = itemView.findViewById(R.id.date_view);
            hours = itemView.findViewById(R.id.hours_view);
            miles = itemView.findViewById(R.id.miles_view);

            this.onTripListener = onTripListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTripListener.onTripClick(getAdapterPosition());
        }
    }

    public interface OnTripListener {
        void onTripClick(int position);
    }
}
