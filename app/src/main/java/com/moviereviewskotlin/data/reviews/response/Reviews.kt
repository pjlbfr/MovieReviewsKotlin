package com.moviereviewskotlin.data.reviews.response

data class Reviews(
    val status: String,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: MutableList<Review>
)