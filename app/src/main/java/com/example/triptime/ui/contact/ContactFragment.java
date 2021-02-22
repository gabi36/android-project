package com.example.triptime.ui.contact;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triptime.AddTripActivity;
import com.example.triptime.R;

import org.w3c.dom.Text;

public class ContactFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        View root = inflater.inflate(R.layout.contact_fragment, container, false);
        TextView textView = root.findViewById(R.id.text_contact);
        contactViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });

        return root;
    }

}