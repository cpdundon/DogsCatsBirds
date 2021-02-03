package com.example.dogwebsite.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dogwebsite.R;
import com.example.dogwebsite.viewModel.MainViewModel;
import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.isSuccessful.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccessful) {
                ((MaterialTextView) findViewById(R.id.tv_rtn_time))
                        .setText(String.valueOf(System.currentTimeMillis()));
                String msg = "Call: " + String.valueOf(isSuccessful);
                //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_fetch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(((AppCompatEditText) findViewById(R.id.et_count)).getText().toString());
                viewModel.fetchShibes(count);
            }
        });
    }
}