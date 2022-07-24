package com.hardcoder.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hardcoder.movieapp.core.model.AllMovieFeaturesResponse
import com.hardcoder.movieapp.databinding.RowLayoutMovieItemBinding
import com.hardcoder.movieapp.utils.Constants
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private val glide: RequestManager) :
    ListAdapter<AllMovieFeaturesResponse, MovieListAdapter.MovieListViewHolder>(ResultXDiffCalBack) {

    object ResultXDiffCalBack : DiffUtil.ItemCallback<AllMovieFeaturesResponse>() {
        override fun areItemsTheSame(
            oldItem: AllMovieFeaturesResponse,
            newItem: AllMovieFeaturesResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AllMovieFeaturesResponse,
            newItem: AllMovieFeaturesResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            RowLayoutMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieListViewHolder(private val binding: RowLayoutMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllMovieFeaturesResponse) = with(binding) {
            tvMovieName.text = item.originalTitle
            ratingBar.rating = item.voteAverage.toFloat()
            glide.load(Constants.IMAGE_URL + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade()).into(ivMovie)
        }
    }

}

