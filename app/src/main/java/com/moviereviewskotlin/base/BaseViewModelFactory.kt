package com.moviereviewskotlin.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class BaseViewModelFactory(creators: Map<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

//    private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>
//
//    constructor(creators: Map<Class<out ViewModel>, Provider<ViewModel>>)


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.




    }



}