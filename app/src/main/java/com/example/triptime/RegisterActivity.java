package com.example.triptime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.triptime.UserValues.User;
import com.example.triptime.UserValues.UserDatabase;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import static com.example.triptime.NavigationActivity.EMAIL_KEY;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextPassword, editTextPasswordVerification;
    private String username, mail, password, passwordVerification;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordVerification = findViewById(R.id.editTextPasswordVerification);
    }

    public void registerButtonOnClick(View view) {
        username = editTextUsername.getText().toString();
        mail = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        passwordVerification = editTextPasswordVerification.getText().toString();

        if (username.length() > 0 && mail.length() > 0 && password.length() > 0 && passwordVerification.length() > 0) {
            if (Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                if (password.equals(passwordVerification)) {
                    user = new User(username, mail, password);
                    UserDatabase db = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "users").allowMainThreadQueries().build();
                    db.userDao().insert(user);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(EMAIL_KEY, mail);
                    editor.apply();

                    Intent intent = new Intent(RegisterActivity.this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Snackbar.make(view, getString(R.string.password_error), BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
            else{
                Snackbar.make(view, getString(R.string.invalid_mail), BaseTransientBottomBar.LENGTH_LONG).show();
            }
        }
        else{
            Snackbar.make(view, getString(R.string.fields_error) , BaseTransientBottomBar.LENGTH_LONG).show();
        }
    }
}