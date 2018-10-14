package com.example.avi_pc.youtubedemo.injection.module;

import android.app.Activity;
import android.content.Context;


import com.example.avi_pc.youtubedemo.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
}