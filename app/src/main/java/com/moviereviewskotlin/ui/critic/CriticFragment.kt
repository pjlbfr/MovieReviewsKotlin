package com.moviereviewskotlin.ui.critic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.moviereviewskotlin.GlideApp
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseFragment
import com.moviereviewskotlin.data.critics.parameter.CriticReviewsParams
import com.moviereviewskotlin.data.critics.response.Critic
import com.moviereviewskotlin.data.reviews.response.Reviews
import com.paginate.Paginate
import kotlinx.android.synthetic.main.fragment_critic.*
import javax.inject.Inject

class CriticFragment :
    BaseFragment(), Paginate.Callbacks, BaseAdapter.OnItemClickListener {

    companion object {
        val TAG = CriticFragment::class.java.simpleName

        fun newInstance(critic: Critic): CriticFragment {

            val bundle = Bundle()
            bundle.putParcelable(TAG, critic)
            val fragment = CriticFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CriticViewModel

    private val adapter = CriticReviewsAdapter(mutableListOf(), this)

    private var hasMoreReviews = true
    private var loading = false

    override fun getFragmentLayout(): Int = R.layout.fragment_critic

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val critic = arguments?.getParcelable(TAG) as Critic?
        initCritic(critic)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CriticViewModel::class.java)
        viewModel.getReviews().observe(this, reviewsObserver())

        initRecyclerView()
    }

    private fun initCritic(critic: Critic?) {
        tvNameCritic.text = critic?.display_name

        tvStatusCritic.text = critic?.status

        val src = critic?.multimedia?.resource?.src

        GlideApp.with(this)
            .load(src)
            .apply(RequestOptions().placeholder(R.drawable.critic_default).centerCrop())
            .into(imageCritic)

        if (!critic?.bio.equals("")) {

            tvBioCritic.visibility = View.VISIBLE
            tvBioCritic.text = HtmlCompat.fromHtml(critic?.bio!!, HtmlCompat.FROM_HTML_MODE_LEGACY)

            cardCritic.setOnTouchListener(object : OnSwipeTouchListener(context) {
                override fun onSwipeTop() {
                    tvBioCritic.visibility = View.GONE
                }

                override fun onSwipeBottom() {
                    tvBioCritic.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun initRecyclerView() {
        rvCriticReviews.layoutManager = LinearLayoutManager(context)
        rvCriticReviews.adapter = adapter
        initPaginate()
    }

    private fun reviewsObserver(): Observer<Reviews> {
        return Observer { result ->
            loading = false
            hasMoreReviews = result.has_more
            adapter.setAllToItems(result.results)
        }
    }

    // Paginate
    private fun initPaginate() {
        Paginate.with(rvCriticReviews, this)
            .setLoadingTriggerThreshold(2)
            .addLoadingListItem(true)
            .setLoadingListItemCreator(LoadingItemCreator())
            .build()
    }

    override fun onLoadMore() {
        loading = true
        viewModel.reviewsRequest(CriticReviewsParams(adapter.itemCount, tvNameCritic.text.toString()))
    }

    override fun isLoading(): Boolean {
        return loading
    }

    override fun hasLoadedAllItems(): Boolean {
        return !hasMoreReviews
    }

    // Adapter Item Click
    override fun onItemClick(position: Int) {

    }
}