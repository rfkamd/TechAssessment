package com.example.assessment.views.main;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assessment.R;
import com.example.assessment.databinding.FragmentMasterBinding;
import com.example.assessment.network.DataWrapper;
import com.example.assessment.network.models.News;
import com.example.assessment.views.adapters.ArticleAdapter;

import android.arch.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    private Context context;
    private MainViewModel viewModel;
    private FragmentMasterBinding binding;
    private ArticleAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    // Required empty public constructor
    public MasterFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_master, container, false);
        binding.srf1.setOnRefreshListener(refreshListener);
        init();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            init();
        }
    };

    private void init(){

        binding.srf1.setRefreshing(true);

        viewModel.apiLiveData.getMostViewed().observe(this, new Observer<DataWrapper<News>>() {
            @Override
            public void onChanged(@Nullable DataWrapper<News> newsDataWrapper) {
                binding.srf1.setRefreshing(false);
                //if exception print and return
                if(newsDataWrapper.exception != null){
                    newsDataWrapper.exception.printStackTrace();
                    return;
                }
                //proceed to display data
                adapter = new ArticleAdapter(newsDataWrapper.data.results);
                //bind to layout Manager
                binding.rcArcticles.setLayoutManager(new LinearLayoutManager(context));
                binding.rcArcticles.setAdapter(adapter);

            }
        });
    }
}
