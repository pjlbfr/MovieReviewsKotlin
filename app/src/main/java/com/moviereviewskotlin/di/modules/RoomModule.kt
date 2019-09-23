package com.moviereviewskotlin.di.modules

import androidx.room.Room
import com.moviereviewskotlin.MovieApp
import com.moviereviewskotlin.room.MRDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoom(app: MovieApp) =
        Room.databaseBuilder(app.applicationContext, MRDatabase::class.java, "mr_db")
//            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providesCriticDao(db: MRDatabase) = db.criticDao()

    @Provides
    fun providesReviewDao(db: MRDatabase) = db.reviewDao()
}