package com.moviereviewskotlin.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moviereviewskotlin.data.critics.response.MultimediaCritic
import com.moviereviewskotlin.data.reviews.response.MultimediaReview

class MultimediaReviewConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToSomeObject(data: String?): MultimediaReview {

        val listType = object : TypeToken<MultimediaCritic>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectToString(someObjects: MultimediaReview): String {
        return gson.toJson(someObjects)
    }
}