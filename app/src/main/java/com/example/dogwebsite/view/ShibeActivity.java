package com.example.dogwebsite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dogwebsite.adapter.ShibeAdapter;
import com.example.dogwebsite.databinding.ActivityMainBinding;
import com.example.dogwebsite.utils.Constants;
import com.example.dogwebsite.viewModel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class ShibeActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private boolean gridFormat = false;
    private String animal = "shibes";
    private boolean encrypted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Get data from intent
        animal = getIntent().getStringExtra(Constants.SHIBE_ACTIVITY_PARAM_STRING);
        int intData = getIntent().getIntExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, 0);
        encrypted = getIntent().getBooleanExtra(Constants.SHIBE_ACTIVITY_PARAM_ENCRYPTED, false);

        setUpListeners();
        setUpObservers();

        Toast.makeText(this, animal, Toast.LENGTH_SHORT).show();
        viewModel.fetchShibes(encrypted, animal, intData);
    }

    private void setUpObservers() {
        viewModel.isSuccessful.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                ((MaterialTextView) binding.tvRtnTime)
                        .setText(String.valueOf(System.currentTimeMillis()));
                String msg = "Call: " + String.valueOf(isSuccessful);
                Toast.makeText(ShibeActivity.this, "Call: " + isSuccessful, Toast.LENGTH_SHORT).show();
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

                viewModel.fetchShibes(encrypted, animal, count);
            }
        });

        binding.btnFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridFormat = !gridFormat;
                setGridLayoutMgr(gridFormat);
            }
        });

        setGridLayoutMgr(false);
    }

    private void setGridLayoutMgr(boolean grid) {
        if (grid) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            binding.rvImageList.setLayoutManager(gridLayoutManager);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.rvImageList.setLayoutManager(linearLayoutManager);
        }
    }
}