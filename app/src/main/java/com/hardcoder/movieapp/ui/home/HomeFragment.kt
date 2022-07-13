package com.hardcoder.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.ui.base.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentHomeBinding
import com.hardcoder.movieapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.movie.collect {
                Log.e("TAG", "onViewCreated: ${it.movieList?.results}", )
            }
        }
    }

    override fun getFragmentView() = R.layout.fragment_home

}