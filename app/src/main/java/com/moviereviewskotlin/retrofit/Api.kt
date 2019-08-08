package com.moviereviewskotlin.retrofit

import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("reviews/search.json?api-key=Gj5bNb9TOHnGuCGAWTiG1MPlcOUUc6WG")
    fun getReviewsObservable(@Query("offset") offset: Int, @Query("query") title: String, @Query("publication-date") publication_date: String): Observable<Reviews>

    @GET("critics/{name}.json?api-key=Gj5bNb9TOHnGuCGAWTiG1MPlcOUUc6WG")
    fun getCritics(@Path("name") name: String): Observable<Critics>

    @GET("reviews/search.json?api-key=Gj5bNb9TOHnGuCGAWTiG1MPlcOUUc6WG")
    fun getCriticReviews(@Query("offset") offset: Int, @Query("reviewer") reviewer: String): Observable<Reviews>
}