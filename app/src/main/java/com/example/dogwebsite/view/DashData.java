package com.example.dogwebsite.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.dogwebsite.databinding.ActivityDashBoardBinding;
import com.example.dogwebsite.utils.Constants;

public class DashData {
    private int count;
    private String animal;
    private boolean encrypted;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    DashData (int count, String animal, boolean encrypted) {
        this.count = count;
        this.animal = animal;
        this.encrypted = encrypted;
    }

    DashData (ActivityDashBoardBinding binding) {
        SharedPreferences sharedPref = binding.getRoot().getContext().getSharedPreferences(Constants.SHIBE_STORE_NAME, Context.MODE_PRIVATE);
        count = sharedPref.getInt(Constants.SHIBE_STORE_PARAM_COUNT, 0);
        animal = sharedPref.getString(Constants.SHIBE_STORE_PARAM_ANIMAL, "shibe");
        encrypted = sharedPref.getBoolean(Constants.SHIBE_STORE_PARAM_ENCRYPTED, true);
    }

    void stashUIData(ActivityDashBoardBinding binding) {
        SharedPreferences sharedPref = binding.getRoot().getContext().getSharedPreferences(Constants.SHIBE_STORE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(Constants.SHIBE_STORE_PARAM_COUNT, count);
        editor.putString(Constants.SHIBE_STORE_PARAM_ANIMAL, animal);
        editor.putBoolean(Constants.SHIBE_ACTIVITY_PARAM_ENCRYPTED, encrypted);
        editor.apply();
    }
}
