package com.example.assessment.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assessment.R;
import com.example.assessment.network.models.MediaMetadata;
import com.example.assessment.network.models.Medium;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;
import static com.bumptech.glide.request.RequestOptions.sizeMultiplierOf;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private List<Medium> images;
    private LayoutInflater inflater;

    public SliderAdapter(Context context, List<Medium> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Medium medium = images.get(position);
        View view = inflater.inflate(R.layout.slider_image, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtCaption = view.findViewById(R.id.txtCaption);
        txtCaption.setText(medium.caption);

        String url = null;

        for (MediaMetadata mediaMetadatum : medium.mediaMetadata) {
            if(mediaMetadatum.format.equalsIgnoreCase("Jumbo")){
                url = mediaMetadatum.url;
                break;
            }
        }

        Glide
                .with(context)
                .load(url)
                .apply(sizeMultiplierOf(0.5f))
                .apply(centerCropTransform())
                .placeholder(R.drawable.ic_loading)
                .into(imageView);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
