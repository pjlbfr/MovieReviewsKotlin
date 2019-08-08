package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.remote.CriticsRemoteStore
import com.moviereviewskotlin.data.critics.response.Critics
import io.reactivex.Observable
import javax.inject.Inject

class CriticsRepository @Inject constructor(remoteStore: CriticsRemoteStore): CriticsDataSource {

    private val remoteStore = remoteStore

    override fun getCritics(type: String): Observable<Critics> = remoteStore.getCritics(type)
}