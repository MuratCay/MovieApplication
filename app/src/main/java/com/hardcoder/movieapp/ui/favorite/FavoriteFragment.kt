package com.hardcoder.movieapp.ui.favorite

import androidx.fragment.app.viewModels
import com.hardcoder.movieapp.core.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val viewModel by viewModels<FavoriteViewModel>()

}