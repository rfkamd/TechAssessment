package com.example.assessment.network;

import android.arch.lifecycle.LiveData;
import android.widget.Toast;

import com.example.assessment.network.models.News;
import com.example.assessment.views.main.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiLiveData extends LiveData<DataWrapper<News>> {

    private Retrofit retrofit;
    private final String BASE_URL = "http://api.nytimes.com/svc/";

    private DataWrapper<News> dataWrapper;

    public ApiLiveData() {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public ApiLiveData getMostViewed() {

        dataWrapper = new DataWrapper<>();

        ApiService service = retrofit.create(ApiService.class);
        Call<News> call = service.getMostView();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    dataWrapper.data = response.body();
                } else {
                    dataWrapper.exception = new Exception("NoResponseException");
                }
                postValue(dataWrapper);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                dataWrapper.exception = t;
                postValue(dataWrapper);
            }
        });

        return this;
    }
}
