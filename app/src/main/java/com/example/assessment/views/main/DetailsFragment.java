package com.example.assessment.views.main;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assessment.R;
import com.example.assessment.databinding.FragmentDetailsBinding;
import com.example.assessment.views.adapters.SliderAdapter;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private Context context;
    private MainViewModel viewModel;
    private FragmentDetailsBinding binding;
    private SliderAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    // Required empty public constructor
    public DetailsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        binding.setResult(viewModel.selectedResult);
        adapter = new SliderAdapter(context, viewModel.selectedResult.media);
        binding.viewPager.setAdapter(adapter);

        //start timer to change slides
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (binding.viewPager.getCurrentItem() < viewModel.selectedResult.media.size() - 1) {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
                    } else {
                        binding.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
