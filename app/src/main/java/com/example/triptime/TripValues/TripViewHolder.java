package com.example.triptime.TripValues;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.triptime.AddTripActivity;
import com.example.triptime.R;
import com.example.triptime.ViewItemActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private ImageView imageView;
    private TextView textViewTripDestination, textViewTripName;
    private RatingBar ratingBarCardView;
    private CheckBox favoriteCheckBox;
    private MaterialCardView cardView;

    private int tripId;
    public static final String EDIT_TRIP_KEY = "EDIT_TRIP_KEY";
    public static final String SAVE_FAVORITE_STATUS = "SAVE_FAVORITE_STATUS";

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        TripDatabase db = Room.databaseBuilder(itemView.getContext(), TripDatabase.class, "trips").allowMainThreadQueries().build();
        TripDao dao = db.tripDao();

        imageView = itemView.findViewById(R.id.imageViewCardView);
        textViewTripDestination = itemView.findViewById(R.id.textViewTripDestination);
        textViewTripName = itemView.findViewById(R.id.textViewTripName);
        ratingBarCardView = itemView.findViewById(R.id.ratingBarCardView);
        favoriteCheckBox = itemView.findViewById(R.id.favoriteButton);
        cardView = itemView.findViewById(R.id.tripItemCardView);
        cardView.setOnLongClickListener(this);
        cardView.setOnClickListener(this);

        favoriteCheckBox.setOnClickListener(v -> {
            Trip currentTrip = dao.getTrip(tripId);
            currentTrip.setFavStatus(!currentTrip.isFavStatus());
            dao.update(currentTrip);
            Snackbar.make(v, R.string.favotite_trip, BaseTransientBottomBar.LENGTH_LONG).show();
        });
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextViewTripDestination() {
        return textViewTripDestination;
    }

    public TextView getTextViewTripName() {
        return textViewTripName;
    }

    public RatingBar getRatingBarCardView() {
        return ratingBarCardView;
    }

    public CheckBox getFavoriteCheckBox() {
        return favoriteCheckBox;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void setCardView(MaterialCardView cardView) {
        this.cardView = cardView;
    }


    @Override
    public void onClick(View v) {
        Snackbar.make(v, "ON CLICK", BaseTransientBottomBar.LENGTH_LONG).show();
        Intent i = new Intent(v.getContext(), ViewItemActivity.class);
        v.getContext().startActivity(i);
    }

    @Override
    public boolean onLongClick(View v) {
        Snackbar.make(v, "Long CLICK", BaseTransientBottomBar.LENGTH_LONG).show();
        Intent i = new Intent(v.getContext(), AddTripActivity.class);
        i.putExtra(EDIT_TRIP_KEY, tripId);
        v.getContext().startActivity(i);
        return true;
    }
}
