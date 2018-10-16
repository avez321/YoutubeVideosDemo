package com.example.avi_pc.youtubedemo.activity.home;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.base.BaseActivity;
import com.example.avi_pc.youtubedemo.databinding.ActivityHomeBinding;
import com.example.avi_pc.youtubedemo.model.Item;

import java.util.List;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity implements HomeView {
    private ActivityHomeBinding activityHomeBinding;

    @Inject
    HomePresenter homePresenter;

    @Inject
    YoutubeVideoAdapter youtubeVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        getActivityComponent().inject(this);
        homePresenter.attachView(this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityHomeBinding.rvYoutubeList.setLayoutManager(linearLayoutManager);
        activityHomeBinding.rvYoutubeList.setAdapter(youtubeVideoAdapter);


        homePresenter.getComedyVideosByRelevance(true);

        activityHomeBinding.swipeToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getComedyVideosByRelevance(true);
                activityHomeBinding.swipeToRefreshLayout.setRefreshing(false);
            }
        });


        activityHomeBinding.rvYoutubeList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                boolean showVisibleItemPosition = (visibleItemCount + firstVisibleItemPosition) >= totalItemCount;
                homePresenter.onScrolled(dy, activityHomeBinding.rvYoutubeList.canScrollVertically(1), showVisibleItemPosition);
            }
        });
    }

    @Override
    public void showVideoData(List<Item> items) {
        youtubeVideoAdapter.setList(items);
    }

    @Override
    public void addVideoData(List<Item> items) {
        youtubeVideoAdapter.addList(items);
    }

    @Override
    public void setLayoutMoreVisibility(int visibility) {
        activityHomeBinding.loadingMoreLayout.setVisibility(visibility);
    }
}
