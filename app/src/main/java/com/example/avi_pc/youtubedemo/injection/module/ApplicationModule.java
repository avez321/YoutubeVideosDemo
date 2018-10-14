package com.example.avi_pc.youtubedemo.injection.module;

import android.app.Application;
import android.content.Context;


import com.example.avi_pc.youtubedemo.injection.ApplicationContext;
import com.example.avi_pc.youtubedemo.remote.RetrofitFactory;
import com.example.avi_pc.youtubedemo.remote.YoutubeDemoAppApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit() {
        return RetrofitFactory.getRetrofit(mApplication);
    }

    @Singleton
    @Provides
    YoutubeDemoAppApi providesMobilManiApiService(Retrofit retrofit) {
        return retrofit.create(YoutubeDemoAppApi.class);
    }

}