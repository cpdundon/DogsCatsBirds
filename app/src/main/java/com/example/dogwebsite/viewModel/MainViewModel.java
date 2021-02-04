package com.example.dogwebsite.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dogwebsite.repo.ShibeRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _isSuccessful = new MutableLiveData<>();
    public LiveData isSuccessful = (LiveData) _isSuccessful;
    private MutableLiveData<String> _firstURL = new MutableLiveData<>();
    public LiveData firstURL = (LiveData) _firstURL;
    private MutableLiveData<List<String>> _uRLList = new MutableLiveData<>();
    //private LiveData<List<String>> uRLList = _uRLList;

    public LiveData<List<String>> getLiveData() {
        return _uRLList;
    }

    public void fetchShibes(int count) {
        ShibeRepository.getInstance().getShibes(count).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(
                    @NotNull Call<List<String>> call,
                    @NotNull Response<List<String>> response) {
                _isSuccessful.setValue(response.body().size() > 0);
                _firstURL.setValue(response.body().get(0));
                _uRLList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                _isSuccessful.setValue(false);
                _firstURL.setValue("");
            }
        });
    }
}
