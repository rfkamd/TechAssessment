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

    /**
     * Set image from a url into image view using glide
     * @param imageView ImageView
     * @param url Image url
     */
    @BindingAdapter("url")
    public static void setUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    /**
     * Set number of views in TextView
     * @param textView TextView
     * @param num number of views in long
     */
    @BindingAdapter("views")
    public static void setViews(TextView textView, long num){
        textView.setText(String.valueOf(num));
    }

    /**
     * Select and set square 320 image in ImageView
     * @param imageView ImageView
     * @param result result object to get image from
     */
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

    /**
     * Set Hyperlink on a TextView to show url
     * @param textView TextView
     * @param url URL to open in chrome
     */
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
