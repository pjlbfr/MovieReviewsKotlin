package com.moviereviewskotlin.data.critics.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Critic(
  //  val id: String,
    val bio: String,
    val status: String,
    val seo_name: String,
    val sort_name: String,
    val display_name: String,
    val multimedia: MultimediaCritic?
) : Parcelable