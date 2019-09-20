package com.moviereviewskotlin.room.dao

import androidx.room.*
import com.moviereviewskotlin.data.reviews.response.Review

@Dao
interface ReviewDao {

    @Query("SELECT * FROM review")
    fun getAll(): MutableList<Review>

    @Query("DELETE FROM review")
    fun deleteAll()

    @Query("SELECT * FROM review WHERE id = :id")
    fun getById(id: Long): Review

    @Insert
    fun insert(review: Review)

    @Insert
    fun insert(reviews: MutableList<Review>)

    @Update
    fun update(review: Review)

    @Delete
    fun delete(review: Review)


}