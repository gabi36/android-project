package com.example.triptime;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.triptime.TripValues.Trip;
import com.example.triptime.TripValues.TripDatabase;
import com.example.triptime.UserValues.UserDatabase;

import java.util.Calendar;

import static com.example.triptime.NavigationActivity.EMAIL_KEY;
import static com.example.triptime.TripValues.TripViewHolder.EDIT_TRIP_KEY;

public class AddTripActivity extends AppCompatActivity {

    EditText editTextTripDestination, editTextTripName;
    RadioButton radioButtonCityBreak, radioButtonSeaSide, radioButtonMountains;
    SeekBar priceSeekBar;
    TextView textViewStartDate, textViewEndDate;
    RatingBar ratingBarStars;
    Button buttonSaveTrip, buttonStartDate, buttonEndDate;

    String tripDestination, tripName;
    float ratingBar;

    Intent i;
    Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        editTextTripDestination = findViewById(R.id.editTextTripDestination);
        editTextTripName = findViewById(R.id.editTextTripName);
        radioButtonCityBreak = findViewById(R.id.cityBreak);
        radioButtonSeaSide = findViewById(R.id.seaSide);
        radioButtonMountains = findViewById(R.id.mountains);
        priceSeekBar = findViewById(R.id.priceSeekBar);
        textViewStartDate = findViewById(R.id.stardDate);
        textViewEndDate = findViewById(R.id.endDate);
        ratingBarStars = findViewById(R.id.ratingBar);
        buttonStartDate = findViewById(R.id.buttonPickStartDate);
        buttonEndDate = findViewById(R.id.buttonPickEndDate);
        buttonSaveTrip = findViewById(R.id.buttonSaveTrip);

        i = getIntent();

        if (i.getExtras() != null) {
            buttonSaveTrip.setText(R.string.edit_trip);
            int id = i.getIntExtra(EDIT_TRIP_KEY, 0);
            TripDatabase tripDb = Room.databaseBuilder(getApplicationContext(), TripDatabase.class, "trips").allowMainThreadQueries().build();
            trip = tripDb.tripDao().getTrip(id);

            editTextTripDestination.setText(trip.getTripDestination());
            editTextTripName.setText(trip.getTripName());
            ratingBarStars.setRating(trip.getRatingBar());
        }
    }

    public void pickADate(TextView textView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
        datePickerDialog.show();
        textView.setText("Year: " + year + ", month: " + month + ", day: " + day);
    }


    public void buttonPickStartDate(View view) {
        pickADate(textViewStartDate);
    }

    public void buttonPickEndDate(View view) {
        pickADate(textViewEndDate);
    }

    public void buttonAddTrip(View view) {
        tripDestination = editTextTripDestination.getText().toString();
        tripName = editTextTripName.getText().toString();
        ratingBar = ratingBarStars.getRating();

        TripDatabase tripDb = Room.databaseBuilder(getApplicationContext(), TripDatabase.class, "trips").allowMainThreadQueries().build();

        if (i.getExtras() != null) {
            trip.setTripDestination(tripDestination);
            trip.setTripName(tripName);
            trip.setRatingBar(ratingBar);
            tripDb.tripDao().update(trip);
        } else {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            UserDatabase userDb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
            int userId = userDb.userDao().getUserMail(preferences.getString(EMAIL_KEY, "")).getId();

            Toast.makeText(this, getString(R.string.trip_added), Toast.LENGTH_LONG).show();
            tripDb.tripDao().insertAll(new Trip(tripDestination, tripName, ratingBar, false, userId));

        }
        Intent i = new Intent(view.getContext(), NavigationActivity.class);
        startActivity(i);
        finish();
    }
}