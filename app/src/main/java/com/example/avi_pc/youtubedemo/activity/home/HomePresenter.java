package com.example.avi_pc.youtubedemo.activity.home;

import com.example.avi_pc.youtubedemo.base.BasePresenter;
import com.example.avi_pc.youtubedemo.remote.YoutubeDemoAppApi;


import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeView> {

    @Inject
    HomePresenter(YoutubeDemoAppApi youtubeDemoAppApi){ }

}
