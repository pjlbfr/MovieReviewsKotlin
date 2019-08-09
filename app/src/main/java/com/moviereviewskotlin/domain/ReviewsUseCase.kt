package com.moviereviewskotlin.domain

import com.moviereviewskotlin.data.reviews.ReviewsRepository
import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import io.reactivex.Observable
import javax.inject.Inject

class ReviewsUseCase @Inject constructor(repository: ReviewsRepository) : UseCase<ReviewsParams, Observable<Reviews>>() {

    private val dataSource = repository

    override fun executeUseCase(requestValues: ReviewsParams): Observable<Reviews> = dataSource.getReviews(requestValues)
}