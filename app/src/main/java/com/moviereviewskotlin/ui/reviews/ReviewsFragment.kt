package com.moviereviewskotlin.ui.reviews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.ui.critic.LoadingItemCreator
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_reviews.*
import javax.inject.Inject

class ReviewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnItemClickListener, Paginate.Callbacks {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewsViewModel

    private val adapter = ReviewsAdapter(mutableListOf(), this)

    private var hasMoreReviews = true
    private var loading = false

    override fun getFragmentLayout(): Int = R.layout.fragment_reviews

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshReviews.setOnRefreshListener(this)

        viewModel = ViewModelProviders.of(this, viewmodelFactory).get(ReviewsViewModel::class.java)
        viewModel.getReviews().observe(this, reviewsObserver())

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvReviews.layoutManager = LinearLayoutManager(context)
        rvReviews.adapter = adapter
        initPaginate()
    }

    override fun onRefresh() {

    }

    override fun onItemClick(position: Int) {

    }

    private fun reviewsObserver(): Observer<Reviews> {
        return Observer { result ->
            loading = false
            hasMoreReviews = result.has_more
            adapter.setAllToItems(result.results)
            Log.e("goodday", result.toString())
        }
    }

    // Paginate
    private fun initPaginate() {
        Paginate.with(rvReviews, this)
            .setLoadingTriggerThreshold(2)
            .addLoadingListItem(true)
            .setLoadingListItemCreator(LoadingItemCreator())
            .build()
    }

    override fun onLoadMore() {
        loading = true
        viewModel.reviewsRequest(ReviewsParams(adapter.itemCount, etSearchReview.text.toString(), ""))
    }

    override fun isLoading(): Boolean {
        return loading
    }

    override fun hasLoadedAllItems(): Boolean {
        return !hasMoreReviews
    }


}