package com.moviereviewskotlin.ui.critics

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.base.RxSerachObservable
import com.moviereviewskotlin.data.critics.response.Critics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_critics.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CriticsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnItemClickListener {

    @Inject
    lateinit var router: CriticsRouter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CriticsViewModel

    private val adapter = CriticsAdapter(mutableListOf(), this)

    private val compositeDisposable = CompositeDisposable()

    override fun getFragmentLayout(): Int = R.layout.fragment_critics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshCritics.setOnRefreshListener(this)

        initRecyclerView()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CriticsViewModel::class.java)
        viewModel.getCritics().observe(this, criticsObserver())

        swipeRefreshCritics.isRefreshing = true
        viewModel.criticsRequest()
        initSearch()
    }

    private fun initSearch() {
        compositeDisposable.add(RxSerachObservable.fromView(etSearchCritic)
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(handleSearch())
        )
    }

    private fun handleSearch(): Consumer<String> {
        return Consumer { query ->
            adapter.filter.filter(query)
        }
    }

    override fun onRefresh() {
        swipeRefreshCritics.isRefreshing = true
        viewModel.criticsRequest()
    }

    private fun initRecyclerView() {
        rvCritics.layoutManager = GridLayoutManager(context, 2)
        rvCritics.adapter = adapter
    }

    private fun criticsObserver(): Observer<Critics> {
        return Observer { result ->
            adapter.setItems(result.results)
            if (swipeRefreshCritics.isRefreshing)
                swipeRefreshCritics.isRefreshing = false
        }
    }

    override fun onItemClick(position: Int) {
        router.goToCriticView(activity as Activity, adapter.getItem(position))
    }
}