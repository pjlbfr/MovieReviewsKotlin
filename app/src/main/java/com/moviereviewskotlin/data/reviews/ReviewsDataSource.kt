package com.moviereviewskotlin.data.reviews

import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable

interface ReviewsDataSource {

    fun getReviews(params: ReviewsParams): Observable<Reviews>


}