package com.demo.nyschool.nysSchool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.demo.nyschool.R;
import com.demo.nyschool.databinding.ActivityMainBinding;
import com.demo.nyschool.pojo.NysSchool;
import com.loopj.android.http.AsyncHttpClient;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        mProgressBar = binding.progress;
        mRecyclerView = binding.recylerview;
        subscribeResponseCall();
    }

    private AsyncHttpClient getAsyncClient() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        asyncHttpClient.setResponseTimeout(120000);
        return asyncHttpClient;
    }

    private void subscribeResponseCall() {
        mProgressBar.setVisibility(View.VISIBLE);
        viewModel.getSchoolEvent().observe(this, new Observer<List<NysSchool>>() {
            @Override
            public void onChanged(@Nullable List<NysSchool> nysSchools) {
                mProgressBar.setVisibility(View.GONE);
                MainAdapter  mAdapter = new MainAdapter(nysSchools, MainActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), ((LinearLayoutManager) mLayoutManager).getOrientation());
                mRecyclerView.addItemDecoration(dividerItemDecoration);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        viewModel.getSchoolAPi(getAsyncClient());


    }
}
