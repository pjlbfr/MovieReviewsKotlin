package com.moviereviewskotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.moviereviewskotlin.di.AppComponent
import com.moviereviewskotlin.di.DaggerAppComponent
import com.moviereviewskotlin.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject


public class MovieApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector



}