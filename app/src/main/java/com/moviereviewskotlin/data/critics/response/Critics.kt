package com.moviereviewskotlin.data.critics.response

data class Critics(
    val status: String,
    val copyright: String,
    val num_results: Int,
    val results: MutableList<Critic>
)