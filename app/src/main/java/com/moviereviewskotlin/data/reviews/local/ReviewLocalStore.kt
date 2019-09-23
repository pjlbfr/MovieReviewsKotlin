package com.moviereviewskotlin.data.reviews.local

import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.room.dao.ReviewDao
import io.reactivex.Observable
import javax.inject.Inject

class ReviewLocalStore @Inject constructor(private val reviewDao: ReviewDao) {

    fun getReviews(): Observable<Reviews> =
        Observable.create { e ->
            val result = reviewDao.getAll()
            val reviews = Reviews("", result.size, "", false, result)
            e.onNext(reviews)
            e.onComplete()
        }

    fun setReviews(reviews: Reviews, isUpdate: Boolean): Observable<Reviews> =
        Observable.create { e ->
            if (!isUpdate)
                reviewDao.deleteAll()
            reviewDao.insert(reviews.results)
            e.onNext(reviews)
            e.onComplete()
        }

}