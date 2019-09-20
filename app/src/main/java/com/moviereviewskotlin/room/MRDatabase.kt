package com.moviereviewskotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.reviews.response.Review
import com.moviereviewskotlin.room.converters.LinkConverter
import com.moviereviewskotlin.room.converters.MultimediaCriticConverter
import com.moviereviewskotlin.room.converters.MultimediaReviewConverter
import com.moviereviewskotlin.room.dao.CriticDao
import com.moviereviewskotlin.room.dao.ReviewDao

@Database(entities = [Critic::class, Review::class], version = 1, exportSchema = false)
@TypeConverters(MultimediaCriticConverter::class, LinkConverter::class, MultimediaReviewConverter::class)
abstract class MRDatabase: RoomDatabase() {

        abstract fun criticDao(): CriticDao
        abstract fun reviewDao(): ReviewDao
}