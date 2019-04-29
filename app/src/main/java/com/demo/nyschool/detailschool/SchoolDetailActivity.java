package com.demo.nyschool.detailschool;

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
import android.widget.TextView;

import com.demo.nyschool.R;
import com.demo.nyschool.databinding.ActivityMainBinding;
import com.demo.nyschool.databinding.ActivitySchoolDetailBinding;
import com.demo.nyschool.nysSchool.MainActivity;
import com.demo.nyschool.nysSchool.MainAdapter;
import com.demo.nyschool.nysSchool.MainViewModel;
import com.demo.nyschool.pojo.NysDetail;
import com.demo.nyschool.pojo.NysSchool;
import com.loopj.android.http.AsyncHttpClient;

import java.util.List;

public class SchoolDetailActivity extends AppCompatActivity {
    private ActivitySchoolDetailBinding binding;
    private SchoolDetailViewModel viewModel;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private String schoolName;
    private TextView textView_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            schoolName = extras.getString(MainAdapter.KEY_NAME);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_school_detail);
        viewModel = ViewModelProviders.of(this).get(SchoolDetailViewModel.class);
        binding.setViewModel(viewModel);
        mProgressBar = binding.progress;
        mRecyclerView = binding.recylerview;
       textView_detail= binding.tvDetail;
        subscribeResponseCall();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView_detail.setVisibility(View.GONE);

    }

    private AsyncHttpClient getAsyncClient() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setEnableRedirects(true);
        asyncHttpClient.setResponseTimeout(120000);
        return asyncHttpClient;
    }

    private void subscribeResponseCall() {
        mProgressBar.setVisibility(View.VISIBLE);
        viewModel.getSchoolEvent().observe(this, new Observer<List<NysDetail>>() {
            @Override
            public void onChanged(@Nullable List<NysDetail> nysDetails) {


                mProgressBar.setVisibility(View.GONE);

                if(nysDetails==null||nysDetails.size()==0){
                    textView_detail.setVisibility(View.VISIBLE);
                    return;
                }


                SchoolDetailAdapter  mAdapter = new SchoolDetailAdapter(nysDetails, SchoolDetailActivity.this);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), ((LinearLayoutManager) mLayoutManager).getOrientation());
                mRecyclerView.addItemDecoration(dividerItemDecoration);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);

            }
        });

        viewModel.getSchoolDetalAPi(getAsyncClient(), schoolName,getApplicationContext());

    }
}
