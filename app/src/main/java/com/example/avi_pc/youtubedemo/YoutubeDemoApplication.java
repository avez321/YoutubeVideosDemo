package com.example.avi_pc.youtubedemo;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.example.avi_pc.youtubedemo.injection.component.ApplicationComponent;
import com.example.avi_pc.youtubedemo.injection.component.DaggerApplicationComponent;
import com.example.avi_pc.youtubedemo.injection.module.ApplicationModule;
import io.fabric.sdk.android.Fabric;

public class YoutubeDemoApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
            mApplicationComponent.inject(this);
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static YoutubeDemoApplication get(Context context) {
        return (YoutubeDemoApplication) context.getApplicationContext();
    }
}

