package com.example.assessment.network;

import android.arch.lifecycle.LiveData;

import com.example.assessment.network.models.News;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

        // Creating the interceptor, and setting the log level
        // And adding the interceptor to a client
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient =  new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();

        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if(f.getName().equalsIgnoreCase("orgFacet")) return true;
                if(f.getName().equalsIgnoreCase("perFacet")) return true;
                if(f.getName().equalsIgnoreCase("geoFacet")) return true;
                if(f.getName().equalsIgnoreCase("desFacet")) return true;
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient().addDeserializationExclusionStrategy(strategy)
                .create();

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private GsonConverterFactory buildGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Adding custom deserializers
        gsonBuilder.registerTypeAdapter(News.class, new NewsDeserializer());
        Gson newsGson = gsonBuilder.create();

        return GsonConverterFactory.create(newsGson);
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
