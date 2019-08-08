package com.moviereviewskotlin.data.critics.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resource(
    val type: String,
    val src: String,
    val height: Int,
    val width: Int,
    val credit: String
) :Parcelable