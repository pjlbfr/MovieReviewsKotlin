package com.moviereviewskotlin.data.reviews

import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.retrofit.Api
import io.reactivex.Observable
import javax.inject.Inject

class ReviewsRemoteStore @Inject constructor(api: Api) {

    private val api = api

    fun getReviews(params: ReviewsParams): Observable<Reviews> = api.getReviews(params.offset, params.title, params.publicationDate)
}