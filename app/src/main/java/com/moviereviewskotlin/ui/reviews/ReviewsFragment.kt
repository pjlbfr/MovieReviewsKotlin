package com.moviereviewskotlin.ui.reviews

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun getFragmentLayout(): Int = R.layout.fragment_reviews

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshReviews.setOnRefreshListener(this)

    }

    private fun initRecyclerView(){
        rvReviews.layoutManager = LinearLayoutManager(context)
    }



    override fun onRefresh() {
     //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}