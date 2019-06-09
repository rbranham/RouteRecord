package com.branham.roger.routerecord.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.branham.roger.routerecord.R;
import com.branham.roger.routerecord.models.Trip;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private static final String TAG = "TripAdapter";

    //TODO: Stopped at 11:35ish in coding with Mitch Recyclerview Tutorial
    private ArrayList<Trip> mTrips = new ArrayList<>();
    private Context mContext;

    public TripAdapter(Context context, ArrayList<Trip> tripList){
        mTrips = tripList;
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_list_item_view, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //TODO
        Log.d(TAG, "OnBindViewHolder: called.");

        viewHolder.date.setText(mTrips.get(i).getDate());
        viewHolder.hours.setText(Float.toString(mTrips.get(i).getHours()));
        viewHolder.tripName.setText(mTrips.get(i).getTripName());
        viewHolder.miles.setText(Float.toString(mTrips.get(i).getMiles()));


        /*viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mTrips.get(viewHolder.getAdapterPosition()).getTripName());

                Toast.makeText(mContext, mTrips.get(viewHolder.getAdapterPosition()).getTripName(), Toast.LENGTH_SHORT).show();
            }
        });
         *///null object error?
    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tripName, date, hours, miles;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tripName = itemView.findViewById(R.id.name_view);
            date = itemView.findViewById(R.id.date_view);
            hours = itemView.findViewById(R.id.hours_view);
            miles = itemView.findViewById(R.id.miles_view);
            parentLayout = itemView.findViewById(R.id.trip_history_fragment); //TODO: may want to change this so I can flexible adapter to different views??

        }
    }
}
