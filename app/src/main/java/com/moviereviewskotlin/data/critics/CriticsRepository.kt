package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.local.CriticLocalStore
import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.remote.CriticsRemoteStore
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.util.NetworkUtil
import io.reactivex.Observable
import javax.inject.Inject

class CriticsRepository @Inject constructor(private val remoteStore: CriticsRemoteStore, private val localStore: CriticLocalStore) : CriticsDataSource {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun getCritics(type: String): Observable<MutableList<Critic>> {
       return if (networkUtil.isOnline()) {
        remoteStore.getCritics(type).map { t ->
                localStore.updateCritics(t)
                t.results
            }
        } else {
            localStore.getCritics()
        }
    }

    override fun getReviews(params: CriticReviewsParams): Observable<Reviews> = remoteStore.getReviews(params)
}