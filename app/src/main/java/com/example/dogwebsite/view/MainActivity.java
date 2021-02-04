package com.example.dogwebsite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dogwebsite.R;
import com.example.dogwebsite.adapter.ShibeAdapter;
import com.example.dogwebsite.databinding.ActivityMainBinding;
import com.example.dogwebsite.viewModel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        viewModel.getLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> shibes) {
                ShibeAdapter shibeAdapter = new ShibeAdapter(shibes);
                binding.rvImageList.setAdapter(shibeAdapter);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvImageList.setLayoutManager(linearLayoutManager);
    }
}