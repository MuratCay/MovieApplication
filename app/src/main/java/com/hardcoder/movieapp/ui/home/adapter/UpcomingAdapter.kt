package com.hardcoder.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.hardcoder.movieapp.core.model.ResultX
import com.hardcoder.movieapp.databinding.ItemUpcomingBinding
import com.hardcoder.movieapp.ui.home.adapter.viewholder.UpcomingViewHolder
import javax.inject.Inject

class UpcomingAdapter @Inject constructor(private val glide: RequestManager) :
    ListAdapter<ResultX, UpcomingViewHolder>(ResultXDiffCalBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(
            ItemUpcomingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(getItem(position), glide)
    }
}

class ResultXDiffCalBack : DiffUtil.ItemCallback<ResultX>() {
    override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
        return oldItem.id == newItem.id
    }
}