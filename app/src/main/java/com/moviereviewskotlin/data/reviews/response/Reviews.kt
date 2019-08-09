package com.moviereviewskotlin.data.reviews.response

data class Reviews(
    val status: String,
    val num_results: Int,
    val copyright: String,
    val has_more: Boolean,
    val results: MutableList<Review>
)