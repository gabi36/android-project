package com.example.triptime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.triptime.UserValues.UserDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static final String EMAIL_KEY = "EMAIL_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationActivity.this, AddTripActivity.class));
                finish();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about_us, R.id.nav_contact, R.id.nav_share, R.id.nav_favorite)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View navigationHeader = navigationView.getHeaderView(0);
        TextView textViewUsernameFromHeader = navigationHeader.findViewById(R.id.textViewUsernameFromHeader);
        TextView textViewEmailFromHeader = navigationHeader.findViewById(R.id.textViewEmailFromHeader);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        textViewEmailFromHeader.setText(preferences.getString(EMAIL_KEY, ""));

        UserDatabase db = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
        String userName = db.userDao().getUserMail(preferences.getString(EMAIL_KEY, "")).getName();

        textViewUsernameFromHeader.setText(userName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logOutButton(MenuItem item) {
        Intent intent = new Intent(NavigationActivity.this, SignInActivity.class);
        startActivity(intent);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(EMAIL_KEY, "");
        editor.apply();
        finish();
    }
}