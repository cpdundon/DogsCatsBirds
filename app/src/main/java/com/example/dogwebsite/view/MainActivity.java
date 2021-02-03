package com.example.dogwebsite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dogwebsite.R;
import com.example.dogwebsite.databinding.ActivityMainBinding;
import com.example.dogwebsite.viewModel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setUpListeners();
        setUpObservers();
    }

    private void setUpObservers() {
        viewModel.isSuccessful.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                ((MaterialTextView) binding.tvRtnTime)
                        .setText(String.valueOf(System.currentTimeMillis()));
                String msg = "Call: " + String.valueOf(isSuccessful);
                Toast.makeText(MainActivity.this, "Call: " + isSuccessful, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.uRLList.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String firstURL) {
                ((MaterialTextView) binding.tvUrl)
                        .setText(firstURL);
            }
        });
    }

    private void setUpListeners() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count;
                String entered = ((AppCompatEditText) binding.etCount).getText().toString().trim();

                if (entered.equals("")) {
                    count = 0;
                } else {
                    count = Integer.parseInt(entered);
                }

                viewModel.fetchShibes(count);
            }
        });
    }
}