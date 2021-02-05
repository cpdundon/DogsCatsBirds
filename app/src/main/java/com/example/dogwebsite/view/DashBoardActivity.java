package com.example.dogwebsite.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dogwebsite.databinding.ActivityDashBoardBinding;
import com.example.dogwebsite.utils.Constants;


public class DashBoardActivity extends AppCompatActivity {

    private ActivityDashBoardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashBoardBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        initViews();
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadDefaults();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DashData dashData = scrapeScreen();
        dashData.stashUIData(binding);
    }

    private void initViews() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShibeActivity();
            }
        });
    }

    private void goToShibeActivity() {
        DashData dashData = scrapeScreen();
//        dashData.stashUIData(binding);

        Intent intent = new Intent(this, ShibeActivity.class);

        if (dashData.getAnimal() != null && !dashData.getAnimal().isEmpty())
            intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_STRING, dashData.getAnimal());

        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, dashData.getCount());
        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_ENCRYPTED, dashData.isEncrypted());
        startActivity(intent);
    }

    private void loadDefaults() {
        DashData dashData = new DashData(binding);

        binding.etIntInput.setText(Integer.toString(dashData.getCount()));

        if (dashData.isEncrypted()) {
            binding.radioTrue.toggle();
        } else {
            binding.radioFalse.toggle();
        }

        if (dashData.getAnimal().equals("shibes")) {
            binding.radioShibes.toggle();
        } else if (dashData.getAnimal().equals("cats")) {
            binding.radioCats.toggle();
        } else {
            binding.radioBirds.toggle();
        }
    }
    DashData scrapeScreen() {
        String strData = null;
        int intData = 0;
        boolean encrypted = false;

        if (binding.radioCats.isChecked()) {
            strData = "cats";
        } else if (binding.radioBirds.isChecked()) {
            strData = "birds";
        } else {
            strData = "shibes";
        }

        if (binding.radioTrue.isChecked()) {
            encrypted = true;
        }

        if (binding.etIntInput.getText() != null) {
            String intDataString = binding.etIntInput.getText().toString().trim();
            if (intDataString.equals("")) intDataString = "0";
            intData = Integer.parseInt(intDataString);
        }

        Intent intent = new Intent(this, ShibeActivity.class);

        if (strData != null && !strData.isEmpty())
            intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_STRING, strData);

        DashData dashData = new DashData(intData, strData, encrypted);
        return dashData;
    }
}
