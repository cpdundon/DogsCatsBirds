package com.example.dogwebsite.repo.remote;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShibeService {

    // /com.example.dogwebsite.api/shibes?count=50
    @GET("/api/{animal}")
    Call<List<String>> getShibes(@Path("animal") String animal, @QueryMap Map<String, String> options);
}
