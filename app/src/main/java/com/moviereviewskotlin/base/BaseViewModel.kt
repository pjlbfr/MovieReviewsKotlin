package com.moviereviewskotlin.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    private val errorMessage = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun handleError(throwable: Throwable) {
        errorMessage.value = ServerError(throwable).getErrorMessage()
    }
}