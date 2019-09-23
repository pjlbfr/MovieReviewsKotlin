package com.moviereviewskotlin.data.reviews

import com.moviereviewskotlin.data.reviews.local.ReviewLocalStore
import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.remote.ReviewsRemoteStore
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.util.NetworkUtil
import io.reactivex.Observable
import javax.inject.Inject

class ReviewsRepository @Inject constructor(private val remoteStore: ReviewsRemoteStore, private val localStore: ReviewLocalStore) : ReviewsDataSource {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun getReviews(params: ReviewsParams): Observable<Reviews> =
        if (networkUtil.isOnline()) {
            if (params.offset == 0) {
                remoteStore.getReviews(params).flatMap { t ->
                    localStore.setReviews(t, false)
                }
            } else {
                remoteStore.getReviews(params).flatMap { t ->
                    localStore.setReviews(t, true)
                }
            }
        } else {
            localStore.getReviews()
        }
}