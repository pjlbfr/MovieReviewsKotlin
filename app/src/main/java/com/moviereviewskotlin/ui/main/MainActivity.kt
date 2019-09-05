package com.moviereviewskotlin.ui.main

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseActivity
import kotlinx.android.synthetic.main.actionbar_title.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(this)

        tvReviews.setOnClickListener {
            viewPager.currentItem = 0
        }

        tvCritics.setOnClickListener {
            viewPager.currentItem = 1
        }
    }

    private fun initActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.actionbar_title)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(this, R.color.colorReviews))
        )
        supportActionBar?.elevation = 0F
        setStatusBarColor(R.color.colorReviews)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem != 0)
            viewPager.currentItem = 0
        else
            super.onBackPressed()
    }

    // ViewPager Listeners
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {

        fun setActionBarView(titleResId: Int, colorResId: Int) {
            tvTitle.setText(titleResId)
            appBar.setBackgroundResource(colorResId)
            supportActionBar?.setBackgroundDrawable(
                ColorDrawable(ContextCompat.getColor(this, colorResId))
            )
        }

        fun setTextView(tv: TextView, colorResId: Int, drawableResId: Int) {
            tv.setTextColor(ContextCompat.getColor(this, colorResId))
            tv.setBackgroundResource(drawableResId)
        }

        when (position) {
            0 -> {
                setActionBarView(R.string.reviews, R.color.colorReviews)
                setStatusBarColor(R.color.colorReviews)

                setTextView(tvCritics, R.color.colorWhite, R.drawable.tab_background_critics_unselected)
                setTextView(tvReviews, R.color.colorReviews, R.drawable.tab_background_reviews_selected)
            }
            else -> {
                setActionBarView(R.string.critics, R.color.colorCritics)
                setStatusBarColor(R.color.colorCritics)

                setTextView(tvReviews, R.color.colorWhite, R.drawable.tab_background_reviews_unselected)
                setTextView(tvCritics, R.color.colorCritics, R.drawable.tab_background_critics_selected)
            }
        }
        hideSoftKeyboard(viewPager)
    }
}
