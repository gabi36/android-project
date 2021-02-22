package com.example.triptime.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.triptime.R;
import com.example.triptime.TripValues.Trip;
import com.example.triptime.TripValues.TripAdapter;
import com.example.triptime.TripValues.TripDatabase;
import com.example.triptime.UserValues.User;
import com.example.triptime.UserValues.UserDatabase;

import java.util.List;

import static com.example.triptime.NavigationActivity.EMAIL_KEY;

public class HomeFragment extends Fragment {

    private List<Trip> trips;

    private TripAdapter tripAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.tripRecyclerView);

        addTrip();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new TripAdapter(trips, getActivity()));

        return root;
    }

    public void addTrip() {
        TripDatabase tripsDb = Room.databaseBuilder(getContext(), TripDatabase.class, "trips").allowMainThreadQueries().build();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = preferences.getString(EMAIL_KEY, "");
        UserDatabase userDb = Room.databaseBuilder(getContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
        User user = userDb.userDao().getUserMail(email);
        int id = user.getId();
        trips = tripsDb.tripDao().getUserTrips(id);
    }
}