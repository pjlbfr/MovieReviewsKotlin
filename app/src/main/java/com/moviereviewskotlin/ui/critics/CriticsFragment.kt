package com.moviereviewskotlin.ui.critics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.data.critics.response.Critics
import kotlinx.android.synthetic.main.fragment_critics.*
import javax.inject.Inject

class CriticsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnItemClickListener {

    @Inject
    lateinit var router: CriticsRouter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CriticsViewModel

    private val adapter = CriticsAdapter(mutableListOf(), this)

    override fun getFragmentLayout(): Int = R.layout.fragment_critics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshCritics.setOnRefreshListener(this)

        initRecyclerView()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CriticsViewModel::class.java)
        viewModel.getCritics().observe(this, criticsObserver())

        swipeRefreshCritics.isRefreshing = true
        viewModel.criticsRequest()
    }

    override fun onRefresh() {
        etSearchCritic.setText("")
        swipeRefreshCritics.isRefreshing = true
        viewModel.criticsRequest()
    }

    private fun initRecyclerView(){
        rvCritics.layoutManager = GridLayoutManager(context, 2)
        rvCritics.adapter = adapter
    }

    private fun criticsObserver(): Observer<Critics> {
        return Observer { result ->
            Log.e("goodday", result.toString())
            adapter.setAllItems(result.results)
            if (swipeRefreshCritics.isRefreshing)
                swipeRefreshCritics.isRefreshing = false
        }
    }

    override fun onItemClick(position: Int) {
        Log.e("goodday", "position = $position")
        router.goToCriticView(activity as Activity, adapter.getItem(position))
    }
}