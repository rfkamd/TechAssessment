package com.example.assessment.utils;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assessment.network.models.MediaMetadata;
import com.example.assessment.network.models.Medium;
import com.example.assessment.network.models.News;
import com.example.assessment.network.models.Result;

import java.util.List;

public class CustomBindingAdapter {

    @BindingAdapter("url")
    public static void setUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("views")
    public static void setViews(TextView textView, long num){
        textView.setText(String.valueOf(num));
    }

    @BindingAdapter("imageList")
    public static void setImages(ImageView imageView, Result result){

//        List<Medium> media = news.results.get(0).media;

        List<MediaMetadata> images = result.media.get(0).mediaMetadata;

        for (MediaMetadata image : images) {
            if(image.format.equalsIgnoreCase("square320")){
                setUrl(imageView, image.url);
            }
        }
    }

    @BindingAdapter("link")
    public static void setLink(final TextView textView, final String url){
        textView.setText("View More");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                textView.getContext().startActivity(browserIntent);
            }
        });
    }

}
