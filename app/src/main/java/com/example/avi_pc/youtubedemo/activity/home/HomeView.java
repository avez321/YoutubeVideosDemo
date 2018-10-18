package com.example.avi_pc.youtubedemo.activity.home;

import com.example.avi_pc.youtubedemo.base.MvpView;
import com.example.avi_pc.youtubedemo.model.Item;

import java.util.List;

public interface HomeView extends MvpView {

    void showVideoData(List<Item> items);

    void addVideoData(List<Item> items);

    void setLayoutMoreVisibility(int visibility);

    void hideSwipeLoader();

    void showErrorMsg(String msg);
}
