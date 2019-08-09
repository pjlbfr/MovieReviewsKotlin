package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.remote.CriticsRemoteStore
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable
import javax.inject.Inject

class CriticsRepository @Inject constructor(remoteStore: CriticsRemoteStore) : CriticsDataSource {

    private val remoteStore = remoteStore

    override fun getCritics(type: String): Observable<Critics> = remoteStore.getCritics(type)

    override fun getReviews(params: CriticReviewsParams): Observable<Reviews> = remoteStore.getReviews(params)
}