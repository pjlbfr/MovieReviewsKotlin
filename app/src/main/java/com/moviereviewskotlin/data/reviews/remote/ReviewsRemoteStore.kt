package com.moviereviewskotlin.data.reviews.remote

import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.retrofit.Api
import io.reactivex.Observable
import javax.inject.Inject

class ReviewsRemoteStore @Inject constructor(private val api: Api) {

    fun getReviews(params: ReviewsParams): Observable<Reviews> = api.getReviews(params.offset, params.title, params.publicationDate)
}