package com.moviereviewskotlin.data.reviews

import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable
import javax.inject.Inject

class ReviewsRepository @Inject constructor(remoteStore: ReviewsRemoteStore) : ReviewsDataSource {

    private val remoteStore = remoteStore

    override fun getReviews(params: ReviewsParams): Observable<Reviews> = remoteStore.getReviews(params)
}