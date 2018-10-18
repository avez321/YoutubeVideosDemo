package com.example.avi_pc.youtubedemo.activity.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.avi_pc.youtubedemo.Constants;
import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.base.BaseActivity;
import com.example.avi_pc.youtubedemo.databinding.ActivityHomeBinding;
import com.example.avi_pc.youtubedemo.model.Item;
import com.example.avi_pc.youtubedemo.model.User;
import com.example.avi_pc.youtubedemo.util.DialogUtil;
import com.example.avi_pc.youtubedemo.util.GlideUtil;

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

        initUserData();
        initRecycleView();

        homePresenter.getComedyVideosByRelevance(true, false);

        activityHomeBinding.swipeToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getComedyVideosByRelevance(true, true);
            }
        });
    }

    private void initUserData() {
        User user = getIntent().getParcelableExtra(Constants.USER);
        GlideUtil.showImage(activityHomeBinding.imgProfile, user.getImageUrl(), R.drawable.img_default_profile);
        activityHomeBinding.txtUsername.setText(user.getUsername());
        activityHomeBinding.txtEmail.setText(user.getEmail());
    }

    private void initRecycleView() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        activityHomeBinding.rvYoutubeList.setLayoutManager(linearLayoutManager);
        activityHomeBinding.rvYoutubeList.setAdapter(youtubeVideoAdapter);

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

    @Override
    public void hideSwipeLoader() {
        activityHomeBinding.swipeToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorMsg(String msg) {
        DialogUtil.showToast(msg, Toast.LENGTH_SHORT, this);
    }
}
