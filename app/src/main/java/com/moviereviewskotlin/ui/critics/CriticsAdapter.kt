package com.moviereviewskotlin.ui.critics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.request.RequestOptions
import com.moviereviewskotlin.GlideApp
import com.moviereviewskotlin.R
import com.moviereviewskotlin.base.BaseAdapter
import com.moviereviewskotlin.base.BaseViewHolder
import com.moviereviewskotlin.data.critics.response.Critic
import kotlinx.android.synthetic.main.item_critic.view.*

class CriticsAdapter(items: MutableList<Critic>, listener: OnItemClickListener) :
                            BaseAdapter<Critic, CriticsAdapter.CriticsViewHolder>(items, listener), Filterable {

    private val critics = mutableListOf<Critic>()

    fun setItems(_items: MutableList<Critic>) {
        critics.clear()
        critics.addAll(_items)
        setAllItems(_items)
    }

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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val list = ArrayList<Critic>()
                val filterSeq = constraint.toString().toLowerCase()
                if (filterSeq.isNotEmpty()) {
                    for (critic in critics) {
                        if (critic.display_name.toLowerCase().contains(filterSeq))
                            list.add(critic)
                    }
                } else {
                    list.addAll(critics)
                }

                val results = FilterResults()
                results.values = list
                return results
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                updateItems(results.values as MutableList<Critic>)
            }
        }
    }
}