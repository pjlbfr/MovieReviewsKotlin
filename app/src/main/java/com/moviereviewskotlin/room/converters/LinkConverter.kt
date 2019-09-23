package com.moviereviewskotlin.room.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.moviereviewskotlin.data.reviews.response.Link

class LinkConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToSomeObject(data: String?): Link? {

        return gson.fromJson(data, Link::class.java)
    }

    @TypeConverter
    fun someObjectToString(someObjects: Link?): String? {
        return gson.toJson(someObjects)
    }
}