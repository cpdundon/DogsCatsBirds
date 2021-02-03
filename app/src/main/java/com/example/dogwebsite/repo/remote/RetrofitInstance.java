package com.example.dogwebsite.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "http://shibe.online";
    private static ShibeService INSTANCE = null;

    // Singleton -> Make constructor private
    private RetrofitInstance() {

    }

    public static ShibeService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(getClient()).build().create(ShibeService.class);

        return INSTANCE;
    }
    private static OkHttpClient getClient () {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }
}
