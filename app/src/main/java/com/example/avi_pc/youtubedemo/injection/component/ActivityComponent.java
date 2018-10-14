package com.example.avi_pc.youtubedemo.injection.component;





import com.example.avi_pc.youtubedemo.injection.PerActivity;

import com.example.avi_pc.youtubedemo.injection.module.ActivityModule;


import dagger.Component;



@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


}