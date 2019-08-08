package com.moviereviewskotlin.di.modules

import androidx.lifecycle.ViewModel
import com.moviereviewskotlin.di.ViewModelKey
import com.moviereviewskotlin.ui.critic.CriticViewModel
import com.moviereviewskotlin.ui.critics.CriticsViewModel
import com.moviereviewskotlin.ui.reviews.ReviewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CriticsViewModel::class)
    abstract fun bindCriticsViewModel(viewModel: CriticsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReviewsViewModel::class)
    abstract fun bindReviewsViewModel(viewModel: ReviewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CriticViewModel::class)
    abstract fun bindCriticViewModel(viewModel: CriticViewModel): ViewModel
}