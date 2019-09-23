package com.moviereviewskotlin.data.critics.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Critic(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val bio: String?,
    val status: String?,
    val seo_name: String?,
    val sort_name: String?,
    val display_name: String?,
    val multimedia: MultimediaCritic?
) : Parcelable