package com.moviereviewskotlin.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.moviereviewskotlin.ui.critics.CriticsFragment
import com.moviereviewskotlin.ui.reviews.ReviewsFragment

class ViewPagerAdapter(fm: FragmentManager,behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> ReviewsFragment()
            else -> CriticsFragment()
        }

    override fun getCount(): Int = 2
}