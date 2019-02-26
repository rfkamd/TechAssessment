package com.example.assessment.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomBindingAdapter {

    @BindingAdapter("app:url")
    public void setUrl(String url, ImageView imageView){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

}
