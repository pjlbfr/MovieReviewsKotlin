package com.moviereviewskotlin.ui.critics

import androidx.lifecycle.MutableLiveData
import com.moviereviewskotlin.base.BaseViewModel
import com.moviereviewskotlin.data.critics.response.Critics
import com.moviereviewskotlin.domain.CriticsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CriticsViewModel @Inject constructor(criticsUseCase: CriticsUseCase) : BaseViewModel() {

    private val criticsUseCase = criticsUseCase

    private val critics = MutableLiveData<Critics>()

    fun getCritics() = critics

    fun criticsRequest(){
            compositeDisposable.add(criticsUseCase
                .run("all")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> handleCritics(result)},
                    {error -> handleError(error)}
                )
            )
    }

    private fun handleCritics(s: Critics?) {
        critics.value = s
    }
}