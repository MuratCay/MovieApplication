package com.hardcoder.movieapp.ui.home.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.hardcoder.movieapp.core.model.ResultX
import com.hardcoder.movieapp.databinding.ItemUpcomingBinding
import com.hardcoder.movieapp.utils.Constants.IMAGE_URL

class UpcomingViewHolder(private val binding: ItemUpcomingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ResultX, glide: RequestManager) = with(binding) {
        tvMovieName.text = item.originalTitle
        ratingBar.rating = item.voteAverage.toFloat()
        glide.load(IMAGE_URL + item.posterPath).transition(withCrossFade()).into(ivMovie)
    }
}