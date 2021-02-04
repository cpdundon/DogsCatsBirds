package com.example.dogwebsite.view;

import android.content.Intent;
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

    private void initViews() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToShibeActivity();
            }
        });
    }

    private void goToShibeActivity() {
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

        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_INT, intData);
        intent.putExtra(Constants.SHIBE_ACTIVITY_PARAM_ENCRYPTED, encrypted);
        startActivity(intent);
    }
}
