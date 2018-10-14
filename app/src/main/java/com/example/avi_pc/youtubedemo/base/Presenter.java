package com.example.avi_pc.youtubedemo.base;



interface Presenter<V extends MvpView> {
    void attachView(V mvpView);

    void detachView();
}