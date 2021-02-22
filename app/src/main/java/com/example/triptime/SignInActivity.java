package com.example.triptime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.triptime.UserValues.User;
import com.example.triptime.UserValues.UserDatabase;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    public static final String EMAIL_KEY = "EMAIL_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString(EMAIL_KEY, "").isEmpty()) {
            setContentView(R.layout.activity_sign_in);

            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            editTextEmail = findViewById(R.id.editTextEmail);
            editTextPassword = findViewById(R.id.editTextPassword);
        } else {
            Intent intent = new Intent(SignInActivity.this, NavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signInButtonOnClick(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        UserDatabase db = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
        User user = db.userDao().getUserData(email, password);

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches() && user != null) {
            Intent navigationActivity = new Intent(SignInActivity.this, NavigationActivity.class);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(EMAIL_KEY, email);
            editor.apply();
            startActivity(navigationActivity);
        } else {
            Toast.makeText(this, R.string.invalid_email_adress, Toast.LENGTH_SHORT).show();
        }
    }

    public void registerButtonOnClick(View view) {
        Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}