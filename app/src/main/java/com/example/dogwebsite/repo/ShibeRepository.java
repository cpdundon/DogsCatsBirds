package com.example.dogwebsite.repo;

import com.example.dogwebsite.repo.remote.RetrofitInstance;
import com.example.dogwebsite.repo.remote.ShibeService;

import java.util.List;

import retrofit2.Call;

public class ShibeRepository {
    private static ShibeRepository INSTANCE = null;
    private ShibeRepository() {}

    public Call<List<String>> getShibes(int count) {
        ShibeService shibeService = RetrofitInstance.getInstance();
        return shibeService.getShibes(count);
    }

    public static ShibeRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShibeRepository();
        }
        return INSTANCE;
    }
}
