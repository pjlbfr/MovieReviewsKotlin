package com.moviereviewskotlin.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder>(items: MutableList<T>) : RecyclerView.Adapter<VH>() {

    private var items: MutableList<T>

    init {
        this.items = items
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    constructor(items: MutableList<T>, onItemClickListener: OnItemClickListener) : this(items) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener { onItemClickListener.onItemClick(holder.adapterPosition) }
    }

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T = this.items[position]

    fun getAllItems(): MutableList<T> = items

    fun setAllItems(_items: MutableList<T>) {
        this.items = _items
        notifyDataSetChanged()
    }

    fun setAllToItems(_items: MutableList<T>) {
        this.items.addAll(_items)
        notifyDataSetChanged()
    }

    fun setItem(item: T) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun updateItems(items: MutableList<T>) {
        if (this.items != null) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        } else {
            setAllItems(items)
        }
    }
}