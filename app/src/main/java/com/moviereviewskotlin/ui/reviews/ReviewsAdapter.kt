package com.moviereviewskotlin.ui.reviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.moviereviewskotlin.GlideApp
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseViewHolder
import com.moviereviewskotlin.data.reviews.response.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewsAdapter(items: MutableList<Review>, listener: OnItemClickListener) :
                            BaseAdapter<Review, ReviewsAdapter.ReviewsViewHolder>(items, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val view = LayoutInflater.from(parent.context). inflate(R.layout.item_review, parent, false)
        return ReviewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val view = holder.itemView
        val item = getItem(position)

        view.tvByline.text = item.byline
        view.tvDisplayTitle.text = item.display_title
        view.tvSummaryShort.text = item.summary_short
        view.tvDateUpdated.text = item.date_updated.replace("-", "/")

        GlideApp.with(view.context)
            .load(item.multimedia?.src)
            .apply(RequestOptions().placeholder(R.drawable.review_default).centerCrop())
            .into(view.imageReview)
    }

    class ReviewsViewHolder(itemView: View) : BaseViewHolder(itemView)
}