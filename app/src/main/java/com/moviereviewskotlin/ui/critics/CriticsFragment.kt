package com.moviereviewskotlin.ui.critics

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_critics.*

class CriticsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    override fun getFragmentLayout(): Int = R.layout.fragment_critics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshCritics.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        etSearchCritic.setText("")
        if (swipeRefreshCritics.isRefreshing)
            swipeRefreshCritics.isRefreshing = false
    }

    private fun initRecyclerView(){
        rvCritics.layoutManager = GridLayoutManager(context, 2)
    }



}