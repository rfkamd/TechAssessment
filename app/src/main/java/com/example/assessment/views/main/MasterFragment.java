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
import com.example.assessment.network.models.Result;
import com.example.assessment.views.adapters.ArticleAdapter;
import com.example.assessment.views.adapters.NewsItemClickListener;

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
        init();
        //fetch and show data
        refresh();
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
            //refresh on pull down
            refresh();
        }
    };

    NewsItemClickListener itemClickListener = new NewsItemClickListener() {
        @Override
        public void onNewsItemClick(Result result) {
            viewModel.selectedResult = result;
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new DetailsFragment(), "DetailsFragment")
                    .addToBackStack("DetailsFragment")
                    .commit();
        }
    };

    private void init(){

        //setup swipe refresh layout
        binding.srf1.setOnRefreshListener(refreshListener);

        // setup recyclerview
        adapter = new ArticleAdapter();
        adapter.setOnItemClickListener(itemClickListener);
        //bind to layout Manager
        binding.rcArcticles.setLayoutManager(new LinearLayoutManager(context));
        binding.rcArcticles.setAdapter(adapter);

    }

    //get data from API
    private void refresh() {

        binding.srf1.setRefreshing(true);

        viewModel.apiLiveData.observe(this, new Observer<DataWrapper<News>>() {
            @Override
            public void onChanged(@Nullable DataWrapper<News> newsDataWrapper) {
                binding.srf1.setRefreshing(false);
                //if exception print and return
                if (newsDataWrapper.exception != null) {
                    newsDataWrapper.exception.printStackTrace();
                    return;
                }
                // update adapter and notify about data change
                viewModel.results = newsDataWrapper.data.results;
                adapter.setResults(newsDataWrapper.data.results);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
