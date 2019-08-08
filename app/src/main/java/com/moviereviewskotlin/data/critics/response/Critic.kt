package com.moviereviewskotlin.data.critics.response

data class Critic(
  //  val id: String,
    val display_name: String,
    val sort_name: String,
    val status: String,
    val bio: String,
    val seo_name: String,
    val multimedia: MultimediaCritic
)