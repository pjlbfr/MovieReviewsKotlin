package com.moviereviewskotlin.di.modules

import android.content.Context
import com.moviereviewskotlin.MovieApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplication(app : MovieApp):Context = app


//    @Binds
//    internal abstract fun bindContext(application: Application): Context

//    @Provides
//    @Singleton
//    fun provideMovieApplication() = app
}