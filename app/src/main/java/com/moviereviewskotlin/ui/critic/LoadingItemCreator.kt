package com.moviereviewskotlin.ui.critic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviereviewskotlin.R
import com.paginate.recycler.LoadingListItemCreator

class LoadingItemCreator : LoadingListItemCreator {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_loading_list_item, parent, false)
        return LoadingViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}