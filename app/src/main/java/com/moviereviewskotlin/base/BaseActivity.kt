package com.moviereviewskotlin.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var app: Context

    protected abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
    }

    protected fun setStatusBarColor(color: Int){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = color
        }
    }

    protected fun hideSoftKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    protected fun showMessage(message: String){
        Snackbar.make(window.decorView.rootView, message, Snackbar.LENGTH_LONG).show()
    }

    protected fun showMessage(resId: Int){
        Snackbar.make(window.decorView.rootView, resId, Snackbar.LENGTH_LONG).show()
    }
}