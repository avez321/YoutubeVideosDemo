package com.example.avi_pc.youtubedemo.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.YoutubeDemoApplication;
import com.example.avi_pc.youtubedemo.injection.component.ActivityComponent;
import com.example.avi_pc.youtubedemo.injection.component.DaggerActivityComponent;
import com.example.avi_pc.youtubedemo.injection.module.ActivityModule;
import com.example.avi_pc.youtubedemo.util.DialogUtil;

public class BaseActivity extends AppCompatActivity implements MvpView {
    private ActivityComponent mActivityComponent;
    protected Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(YoutubeDemoApplication.get(this).getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public void showProgressDialog() {
        dialog = new Dialog(this, R.style.AppTheme_FullScreenProgressBar);
        DialogUtil.showPleaseWaitDialog(dialog);
    }

    @Override
    public void hideProgressDialog() {
      DialogUtil.hideDialog(dialog);
    }

}