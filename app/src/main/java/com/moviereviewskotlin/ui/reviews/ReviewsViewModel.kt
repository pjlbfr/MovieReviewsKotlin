package com.moviereviewskotlin.ui.reviews

import androidx.lifecycle.MutableLiveData
import com.moviereviewskotlin.base.BaseViewModel
import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.domain.ReviewsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewsViewModel @Inject constructor(private val reviewsUseCase: ReviewsUseCase) : BaseViewModel() {

    private val reviews = MutableLiveData<Reviews>()

    fun getReviews() = reviews

    fun reviewsRequest(params: ReviewsParams) {
        compositeDisposable.add(reviewsUseCase
            .run(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleReviews(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleReviews(result: Reviews?) {
        reviews.value = result
    }
}