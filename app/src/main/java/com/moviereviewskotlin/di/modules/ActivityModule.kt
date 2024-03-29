package com.moviereviewskotlin.di.modules

import com.moviereviewskotlin.ui.critic.CriticActivity
import com.moviereviewskotlin.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMAinActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCriticActivity(): CriticActivity
}