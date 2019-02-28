package com.example.assessment.views.main;

import android.arch.lifecycle.ViewModel;

import com.example.assessment.network.ApiLiveData;
import com.example.assessment.network.models.Result;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    public ApiLiveData apiLiveData = new ApiLiveData();
    public List<Result> results = new ArrayList<>();

    public Result selectedResult = null;


}
