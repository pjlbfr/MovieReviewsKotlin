package com.moviereviewskotlin.di.modules

import android.app.Application
import androidx.room.Room
import com.moviereviewskotlin.room.MRDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoom(app: Application) =
        Room.databaseBuilder(app.applicationContext, MRDatabase::class.java, "mr_db").build()

    @Singleton
    @Provides
    fun providesCriticDao(db: MRDatabase) = db.criticDao()

    @Singleton
    @Provides
    fun providesReviewDao(db: MRDatabase) = db.reviewDao()
}