package com.example.avi_pc.youtubedemo.activity.home;

import com.example.avi_pc.youtubedemo.base.BasePresenter;
import com.example.avi_pc.youtubedemo.model.YoutubeSearchResponse;
import com.example.avi_pc.youtubedemo.remote.YoutubeDemoAppApi;
import com.example.avi_pc.youtubedemo.remote.observer.CallbackObserverWrapper;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    private YoutubeDemoAppApi youtubeDemoAppApi;

    @Inject
    HomePresenter(YoutubeDemoAppApi youtubeDemoAppApi) {
        this.youtubeDemoAppApi = youtubeDemoAppApi;
    }

    void getComedyVideosByRelevance() {
        getMvpView().showProgressDialog();
        youtubeDemoAppApi.searchVideos("AIzaSyAZudgDqJ9aZx7oxpW2o1mxA27HjOdbceY","snippet",10,"relevance", "items(id/videoId,snippet(channelId,channelTitle,title)),nextPageToken").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CallbackObserverWrapper<YoutubeSearchResponse>() {
                    @Override
                    protected void onSuccess(YoutubeSearchResponse youtubeSearchResponse) {
                        getMvpView().hideProgressDialog();
                    }

                    @Override
                    protected void onFailure(String error) {
                        getMvpView().hideProgressDialog();
                      /*  getMvpView().showErrorMessage(R.string.error_title_msg, error, null);*/
                    }
                });
    }

}
