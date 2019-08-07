package com.moviereviewskotlin.di.modules

import androidx.lifecycle.ViewModelProvider
import com.moviereviewskotlin.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}