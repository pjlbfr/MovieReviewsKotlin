package com.moviereviewskotlin.di.modules

import com.moviereviewskotlin.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMAinActivity(): MainActivity

}