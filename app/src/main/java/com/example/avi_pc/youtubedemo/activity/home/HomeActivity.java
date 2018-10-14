package com.example.avi_pc.youtubedemo.activity.home;



import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.base.BaseActivity;
import com.example.avi_pc.youtubedemo.databinding.ActivityHomeBinding;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity implements HomeView {
    private ActivityHomeBinding activityHomeBinding;

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        getActivityComponent().inject(this);
        homePresenter.attachView(this);
    }

}
