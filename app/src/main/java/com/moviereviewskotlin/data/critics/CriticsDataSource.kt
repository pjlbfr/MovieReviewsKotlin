package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable

interface CriticsDataSource {

    fun getCritics(type: String): Observable<Critics>

    fun getReviews(params: CriticReviewsParams): Observable<Reviews>
}