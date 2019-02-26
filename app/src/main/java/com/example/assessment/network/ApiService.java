package com.example.assessment.network;

import com.example.assessment.network.models.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/mostpopular/v2/mostviewed/all-sections/7.json?api-key=O4rN5vGiCsmZXfTR08v8kzgsbaiIFaDc")
    Call<News> getMostView();

}
