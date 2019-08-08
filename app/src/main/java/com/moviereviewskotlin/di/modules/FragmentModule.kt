package com.moviereviewskotlin.di.modules

import com.moviereviewskotlin.ui.critic.CriticFragment
import com.moviereviewskotlin.ui.critics.CriticsFragment
import com.moviereviewskotlin.ui.reviews.ReviewsFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindCriticsFragment(): CriticsFragment

    @ContributesAndroidInjector
    abstract fun bindReviewsFragment(): ReviewsFragment

    @ContributesAndroidInjector
    abstract fun bindCriticFragment(): CriticFragment
}