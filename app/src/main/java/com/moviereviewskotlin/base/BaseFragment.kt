package com.moviereviewskotlin.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    protected abstract fun getFragmentLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showMessage(message: String){
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }
    }

    protected fun showMessage(resId: Int){
        view?.let { Snackbar.make(it, resId, Snackbar.LENGTH_LONG).show() }
    }


}