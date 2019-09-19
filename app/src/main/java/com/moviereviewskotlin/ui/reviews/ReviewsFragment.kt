package com.moviereviewskotlin.ui.reviews

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.data.reviews.parameter.ReviewsParams
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.moviereviewskotlin.ui.critic.LoadingItemCreator
import com.paginate.Paginate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_reviews.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ReviewsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener,
    BaseAdapter.OnItemClickListener, Paginate.Callbacks, View.OnClickListener {

    @Inject
    lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ReviewsViewModel

    private val adapter = ReviewsAdapter(mutableListOf(), this)

    private var hasMoreReviews = true
    private var loading = false
    private var isSearch = false

    private val calendar: Calendar = Calendar.getInstance()

    override fun getFragmentLayout(): Int = R.layout.fragment_reviews

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshReviews.setOnRefreshListener(this)

        viewModel = ViewModelProviders.of(this, viewmodelFactory).get(ReviewsViewModel::class.java)
        viewModel.getReviews().observe(this, reviewsObserver())

        tvDate.text = getString(
            R.string.dateFormat,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        tvDate.setOnClickListener(this)
        initRecyclerView()
        initSearch()
    }

    private fun initRecyclerView() {
        rvReviews.layoutManager = LinearLayoutManager(context)
        rvReviews.adapter = adapter
        initPaginate()
    }

    override fun onRefresh() {
        reviewRequest(0, etSearchReview.text.toString(), getPublicationDate())
    }

    override fun onItemClick(position: Int) {

    }

    private fun reviewsObserver(): Observer<Reviews> {
        return Observer { result ->
            swipeRefreshReviews.isRefreshing = false
            loading = false
            hasMoreReviews = result.has_more

            if (isSearch) {
                isSearch = false
                adapter.setAllItems(result.results)
            } else {
                adapter.setAllToItems(result.results)
            }
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
        reviewRequest(adapter.itemCount, etSearchReview.text.toString(), getPublicationDate())
    }

    override fun isLoading(): Boolean {
        return loading
    }

    override fun hasLoadedAllItems(): Boolean {
        return !hasMoreReviews
    }

    override fun onClick(view: View?) {
        val date = tvDate.text.split(" / ")

        val datePicker = context?.let {
            DatePickerDialog(
                it,
                callback,
                date[0].toInt(),
                date[1].toInt() - 1,
                date[2].toInt()
            )
        }
        datePicker?.show()
    }

    private val callback = DatePickerDialog.OnDateSetListener { _, year, month, day ->
        tvDate.text = getString(R.string.dateFormat, year, month + 1, day)
    }

    private fun getPublicationDate() = tvDate.text.toString().replace(" / ", "-")

    private fun reviewRequest(itemCount: Int, title: String, publicationDate: String) {
        viewModel.reviewsRequest(
            ReviewsParams(
                itemCount,
                title,
                publicationDate
            )
        )
    }

    private fun initSearch() {
        RxTextView.textChanges(etSearchReview)
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { charSequence ->
                reviewRequest(0, charSequence.toString(), getPublicationDate())
                isSearch = true
                Any()
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}