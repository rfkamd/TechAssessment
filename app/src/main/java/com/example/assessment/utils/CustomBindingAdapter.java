package com.example.assessment.utils;

import android.databinding.BindingAdapter;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assessment.network.models.MediaMetadata;
import com.example.assessment.network.models.Medium;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter("app:url")
    public void setUrl(String url, ImageView imageView){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("app:views")
    public void setViews(int num, TextView textView){
        textView.setText(String.valueOf(num));
    }

    @BindingAdapter("app:images")
    public void setImages(List<Medium> media, ImageView imageView){
        List<MediaMetadata> images = media.get(0).mediaMetadata;

        for (MediaMetadata image : images) {
            if(image.format.equalsIgnoreCase("Standard Thumbnail")){
                setUrl(image.url, imageView);
            }
        }
    }

}
