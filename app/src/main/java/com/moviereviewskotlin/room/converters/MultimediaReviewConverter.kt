package com.moviereviewskotlin.room.converters

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.moviereviewskotlin.data.reviews.response.MultimediaReview

class MultimediaReviewConverter {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    @TypeConverter
    fun stringToSomeObject(data: String?): MultimediaReview? =
        gson.fromJson(data, MultimediaReview::class.java)

    @TypeConverter
    fun someObjectToString(someObjects: MultimediaReview?): String? =
        gson.toJson(someObjects)
}