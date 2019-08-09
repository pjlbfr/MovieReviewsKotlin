package com.moviereviewskotlin.data.reviews.response

data class Review(
    val id: String,
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