package com.moviereviewskotlin.room.converters

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.moviereviewskotlin.data.critics.response.MultimediaCritic

class MultimediaCriticConverter {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    @TypeConverter
    fun stringToSomeObject(data: String?): MultimediaCritic? =
        gson.fromJson(data, MultimediaCritic::class.java)


    @TypeConverter
    fun someObjectToString(someObjects: MultimediaCritic?): String? =
        gson.toJson(someObjects)
}