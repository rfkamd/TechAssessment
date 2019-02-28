package com.example.assessment.views.main;

import android.arch.lifecycle.ViewModel;

import com.example.assessment.network.ApiLiveData;
import com.example.assessment.network.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    // API object to call different endpoints
    public ApiLiveData apiLiveData = new ApiLiveData();
    // results array
    public List<Result> results = new ArrayList<>();
    //selected result
    public Result selectedResult = null;


}
