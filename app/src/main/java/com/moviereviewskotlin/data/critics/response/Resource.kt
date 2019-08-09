package com.moviereviewskotlin.data.critics.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resource(
    val width: Int,
    val height: Int,
    val src: String,
    val type: String,
    val credit: String
) :Parcelable