package com.example.dogwebsite.repo.remote;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {

    // /com.example.dogwebsite.api/shibes?count=50
    @GET("/api/shibes")
    Call<List<String>> getShibes(@Query("count") int count);
}
