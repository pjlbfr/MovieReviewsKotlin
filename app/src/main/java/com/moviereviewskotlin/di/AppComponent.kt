package com.moviereviewskotlin.di

import com.moviereviewskotlin.MovieApp
import com.moviereviewskotlin.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
                AppModule::class,
                AndroidInjectionModule::class,
                ActivityModule::class,
                FragmentModule::class,
                NetworkModule::class,
                RoomModule::class,
                ViewModelModule::class,
                ViewModelFactoryModule::class
          ))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MovieApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MovieApp)
}