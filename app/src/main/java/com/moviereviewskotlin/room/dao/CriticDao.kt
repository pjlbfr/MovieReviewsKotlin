package com.moviereviewskotlin.room.dao

import androidx.room.*
import com.moviereviewskotlin.data.critics.response.Critic

@Dao
interface CriticDao {

    @Query("SELECT * FROM critic")
    fun getAll(): MutableList<Critic>

    @Query("DELETE FROM critic")
    fun deleteAll()

    @Query("SELECT * FROM critic WHERE id = :id")
    fun getById(id: Long): Critic

    @Insert
    fun insert(critic: Critic)

    @Insert
    fun insert(critics: MutableList<Critic>)

    @Update
    fun update(critic: Critic)

    @Delete
    fun delete(critic: Critic)


}