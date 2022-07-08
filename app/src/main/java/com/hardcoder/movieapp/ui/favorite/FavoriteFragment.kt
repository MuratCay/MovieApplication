package com.hardcoder.movieapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.core.base.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getFragmentView() = R.layout.fragment_favorite

}