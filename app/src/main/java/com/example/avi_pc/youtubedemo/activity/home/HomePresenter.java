package com.example.avi_pc.youtubedemo.activity.home;

import android.view.View;

import com.example.avi_pc.youtubedemo.Constants;
import com.example.avi_pc.youtubedemo.base.BasePresenter;
import com.example.avi_pc.youtubedemo.model.Id;
import com.example.avi_pc.youtubedemo.model.Item;
import com.example.avi_pc.youtubedemo.model.YoutubeSearchResponse;
import com.example.avi_pc.youtubedemo.remote.YoutubeDemoAppApi;
import com.example.avi_pc.youtubedemo.remote.observer.CallbackObserverWrapper;


import java.util.List;
import java.util.Observable;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    private YoutubeDemoAppApi youtubeDemoAppApi;
    private String nextPageToken;
    private Item item1;

    @Inject
    HomePresenter(YoutubeDemoAppApi youtubeDemoAppApi) {
        this.youtubeDemoAppApi = youtubeDemoAppApi;
    }

    void getComedyVideosByRelevance(final boolean isFirstApiCall, final boolean isSwipeToRefresh) {
        if (!isSwipeToRefresh) {
            if (isFirstApiCall) {
                getMvpView().showProgressDialog();
            } else {
                getMvpView().setLayoutMoreVisibility(View.VISIBLE);
            }
        }

        youtubeDemoAppApi.searchVideos(Constants.API_KEY, Constants.COMEDY, Constants.SNIPPET, Constants.MAX_RESULT, Constants.RELEVENCE, Constants.QUERY_FIELDS, nextPageToken).flatMapIterable(new Function<YoutubeSearchResponse, Iterable<Item>>() {
            @Override
            public Iterable<Item> apply(YoutubeSearchResponse youtubeSearchResponse) throws Exception {
                nextPageToken = youtubeSearchResponse.getNextPageToken();
                return youtubeSearchResponse.getItems();
            }
        }).flatMap(new Function<Item, ObservableSource<YoutubeSearchResponse>>() {
            @Override
            public ObservableSource<YoutubeSearchResponse> apply(Item item) throws Exception {
                item1 = item;
                return youtubeDemoAppApi.getVideoLikes(item.getId().getVideoId(), Constants.API_KEY, Constants.STATISTICS, Constants.STATISTICS_FIELD);
            }
        }).flatMap(new Function<YoutubeSearchResponse, ObservableSource<Item>>() {
            @Override
            public ObservableSource<Item> apply(final YoutubeSearchResponse youtubeSearchResponse) throws Exception {
                item1.setStatistics(youtubeSearchResponse.getItems().get(0).getStatistics());
                return io.reactivex.Observable.fromCallable(new Callable<Item>() {
                    @Override
                    public Item call() throws Exception {
                        return item1;
                    }
                });
            }
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Item> items) {

                        if (!isSwipeToRefresh) {
                            if (isFirstApiCall) {
                                getMvpView().showVideoData(items);
                                getMvpView().hideProgressDialog();
                            } else {
                                getMvpView().addVideoData(items);
                                getMvpView().setLayoutMoreVisibility(View.GONE);
                            }
                        } else {
                            getMvpView().hideSwipeLoader();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideProgressDialog();
                        getMvpView().setLayoutMoreVisibility(View.GONE);
                        getMvpView().hideSwipeLoader();
                        getMvpView().showErrorMsg(e.toString());
                    }
                });
    }

    public void onScrolled(int dy, boolean canScroll, boolean showVisibleItemPosition) {
        if (dy != 0 && !canScroll) {
            if (showVisibleItemPosition) {
                getComedyVideosByRelevance(false, false);
            }
        }
    }
}
