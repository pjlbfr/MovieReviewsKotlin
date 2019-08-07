package com.moviereviewskotlin.di.modules

import androidx.lifecycle.ViewModel
import com.moviereviewskotlin.di.ViewModelKey
import com.moviereviewskotlin.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel


}