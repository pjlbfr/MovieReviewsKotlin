package com.moviereviewskotlin.data.reviews.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val link: Link,
    val byline: String,
    val headline: String,
    val critics_pick: Int,
    val mpaa_rating: String,
    val opening_date: String,
    val date_updated: String,
    val display_title: String,
    val summary_short: String,
    val publication_date: String,
    val multimedia: MultimediaReview
)