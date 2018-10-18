package com.example.avi_pc.youtubedemo.injection.component;

import android.app.Application;
import android.content.Context;


import com.example.avi_pc.youtubedemo.YoutubeDemoApplication;
import com.example.avi_pc.youtubedemo.injection.ApplicationContext;
import com.example.avi_pc.youtubedemo.injection.module.ApplicationModule;
import com.example.avi_pc.youtubedemo.remote.YoutubeDemoAppApi;


import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(YoutubeDemoApplication youtubeDemoApplication);

    @ApplicationContext
    Context context();

    Application application();

    YoutubeDemoAppApi  youtubeDemoAppApi();

}