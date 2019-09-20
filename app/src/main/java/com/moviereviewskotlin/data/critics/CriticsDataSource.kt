package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable

interface CriticsDataSource {

    fun getCritics(type: String): Observable<MutableList<Critic>>

    fun getReviews(params: CriticReviewsParams): Observable<Reviews>
}