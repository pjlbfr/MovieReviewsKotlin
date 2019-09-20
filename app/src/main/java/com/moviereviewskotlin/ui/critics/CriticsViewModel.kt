package com.moviereviewskotlin.ui.critics

import androidx.lifecycle.MutableLiveData
import com.moviereviewskotlin.base.BaseViewModel
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.domain.CriticsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CriticsViewModel @Inject constructor(private val criticsUseCase: CriticsUseCase) : BaseViewModel() {

    private val critics = MutableLiveData<MutableList<Critic>>()

    fun getCritics() = critics

    fun criticsRequest() {
        compositeDisposable.add(criticsUseCase
            .run("all")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> handleCritics(result) },
                { error -> handleError(error) }
            )
        )
    }

    private fun handleCritics(result: MutableList<Critic>?) {
        critics.value = result
    }
}