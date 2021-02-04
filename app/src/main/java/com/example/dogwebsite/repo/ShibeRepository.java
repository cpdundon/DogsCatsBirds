package com.example.dogwebsite.repo;

import com.example.dogwebsite.repo.remote.RetrofitInstance;
import com.example.dogwebsite.repo.remote.ShibeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class ShibeRepository {
    private static ShibeRepository INSTANCE = null;
    private ShibeRepository() {}

    public Call<List<String>> getShibes(boolean encrypted, String animal, int count) {
        ShibeService shibeService = RetrofitInstance.getInstance();

        Map<String, String> toPass = new HashMap();
        toPass.put("count", Integer.toString(count));
        toPass.put("httpsUrls", Boolean.toString(encrypted));
        return shibeService.getShibes(animal, toPass);
    }

    public static ShibeRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ShibeRepository();
        }
        return INSTANCE;
    }
}
