package com.moviereviewskotlin.domain

import com.moviereviewskotlin.data.critics.CriticsRepository
import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable
import javax.inject.Inject

class CriticReviewsUseCase @Inject constructor(repository: CriticsRepository) :
    UseCase<CriticReviewsParams, Observable<Reviews>>() {

    private val dataSource = repository

    override fun executeUseCase(requestValues: CriticReviewsParams): Observable<Reviews> =
        dataSource.getReviews(requestValues)
}