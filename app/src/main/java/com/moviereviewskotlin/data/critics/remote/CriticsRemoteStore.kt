package com.moviereviewskotlin.data.critics.remote

import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.retrofit.Api
import io.reactivex.Observable
import javax.inject.Inject

class CriticsRemoteStore @Inject constructor(private val api: Api) {

    fun getCritics(type: String): Observable<Critics> = api.getCritics(type)

    fun getReviews(params: CriticReviewsParams): Observable<Reviews> =
        api.getCriticReviews(params.offset, params.reviewer)
}