package com.moviereviewskotlin.data.critics.response

data class Critics(
    val status: String,
    val num_results: Int,
    val copyright: String,
    val results: MutableList<Critic>
)