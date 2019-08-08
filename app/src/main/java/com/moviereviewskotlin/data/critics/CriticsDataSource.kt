package com.moviereviewskotlin.data.critics

import com.moviereviewskotlin.data.critics.response.Critics
import io.reactivex.Observable

interface CriticsDataSource {

    fun getCritics(type: String): Observable<Critics>
}