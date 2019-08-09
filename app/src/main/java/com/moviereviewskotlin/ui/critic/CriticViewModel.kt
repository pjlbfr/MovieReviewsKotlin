package com.moviereviewskotlin.ui.critic

import androidx.lifecycle.MutableLiveData
import com.moviereviewskotlin.base.BaseViewModel
import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.domain.CriticReviewsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CriticViewModel @Inject constructor(criticReviewsUseCase: CriticReviewsUseCase) : BaseViewModel() {

    private val criticReviewsUseCase = criticReviewsUseCase

    private val reviews = MutableLiveData<Reviews>()

    fun getReviews() = reviews

    fun reviewsRequest(params: CriticReviewsParams) {
        compositeDisposable.add(criticReviewsUseCase
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