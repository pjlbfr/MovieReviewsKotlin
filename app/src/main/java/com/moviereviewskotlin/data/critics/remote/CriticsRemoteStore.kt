package com.moviereviewskotlin.data.critics.remote

import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.retrofit.Api
import io.reactivex.Observable
import javax.inject.Inject

class CriticsRemoteStore @Inject constructor(api: Api) {

    private val api = api

    fun getCritics(type: String): Observable<Critics> = api.getCritics(type)




}