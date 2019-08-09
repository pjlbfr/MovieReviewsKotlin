package com.moviereviewskotlin.ui.critics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.moviereviewskotlin.GlideApp
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseViewHolder
import com.moviereviewskotlin.data.critics.response.Critic
import kotlinx.android.synthetic.main.item_critic.view.*

class CriticsAdapter(items: MutableList<Critic>, listener: OnItemClickListener) :
                            BaseAdapter<Critic, CriticsAdapter.CriticsViewHolder>(items, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriticsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_critic, parent, false)
        return CriticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriticsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        val view = holder.itemView
        val item = getItem(position)

        view.tvCriticName.text = item.display_name
        
        GlideApp.with(view.context)
             .load(item.multimedia?.resource?.src)
             .apply(RequestOptions().placeholder(R.drawable.critic_default).centerCrop())
             .into(view.imageCritic)
    }

    class CriticsViewHolder(itemView: View) : BaseViewHolder(itemView)
}