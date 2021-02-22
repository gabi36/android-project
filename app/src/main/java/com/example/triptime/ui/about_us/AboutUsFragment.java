package com.example.triptime.ui.about_us;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.triptime.R;

public class AboutUsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutUsViewModel aboutUsViewModel = new ViewModelProvider(this).get(AboutUsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        final TextView textView = root.findViewById(R.id.text_about_us);
        aboutUsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}