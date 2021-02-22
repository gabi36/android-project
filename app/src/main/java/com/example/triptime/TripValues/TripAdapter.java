package com.example.triptime.TripValues;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triptime.R;

import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private List<Trip> trips;
    private Context context;

    public TripAdapter(List<Trip> trips, FragmentActivity activity) {
        this.trips = trips;
        this.context = activity;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = trips.get(position);

        holder.setTripId(trip.getId());
        holder.getTextViewTripDestination().setText(trip.getTripDestination());
        holder.getTextViewTripName().setText(trip.getTripName());
        holder.getRatingBarCardView().setRating(trip.getRatingBar());
        holder.getRatingBarCardView().setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }
}