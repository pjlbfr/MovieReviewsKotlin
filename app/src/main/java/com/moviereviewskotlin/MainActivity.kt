package com.moviereviewskotlin

import android.content.Context
import android.os.Bundle
import com.moviereviewskotlin.base.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity() {



    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)




    }
}
