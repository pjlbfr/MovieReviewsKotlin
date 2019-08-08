package com.moviereviewskotlin.data.reviews.response

data class Review(
    val id: String,
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Int,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val publication_date: String,
    val opening_date: String,
    val date_updated: String,
    val link: Link,
    val multimedia: MultimediaReview
)