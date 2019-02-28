package com.example.assessment;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;

import com.example.assessment.network.ApiLiveData;
import com.example.assessment.network.DataWrapper;
import com.example.assessment.network.models.News;
import com.example.assessment.views.main.MainViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.*;


public class LiveDataUnitTest {
    @Rule
    public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();

    private MainViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new MainViewModel();
    }

    @Test
    public void testApi() {
        ApiLiveData liveData = viewModel.apiLiveData;

        try {
            TestObserver.test(liveData)
                    .awaitValue()
                    .doOnChanged(new Consumer<DataWrapper<News>>() {
                        @Override
                        public void accept(DataWrapper<News> newsDataWrapper) {
                            assertNull(newsDataWrapper.data);
                        }
                    })
                    .assertNever(new Function<DataWrapper<News>, Boolean>() {
                        @Override
                        public Boolean apply(DataWrapper<News> newsDataWrapper) {
                            if(newsDataWrapper.exception == null){
                                return true;
                            }
                            return false;
                        }
                    });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}