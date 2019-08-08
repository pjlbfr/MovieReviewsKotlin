package com.moviereviewskotlin.ui.critics

import android.content.Context
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

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriticsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_critic, parent, false)
        context = parent.context
        return CriticsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriticsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        holder.itemView.tvCriticName.text = getItem(position).display_name

        val src = getItem(position).multimedia?.resource?.src

        GlideApp.with(context)
             .load(src)
//             .apply(RequestOptions().placeholder(R.drawable.critic_default).centerCrop())
             .into(holder.itemView.imageCritic)

    }


    class CriticsViewHolder(itemView: View) : BaseViewHolder(itemView)
}