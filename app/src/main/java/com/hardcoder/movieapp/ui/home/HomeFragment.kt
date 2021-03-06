package com.hardcoder.movieapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.databinding.FragmentHomeBinding
import com.hardcoder.movieapp.ui.base.BaseFragment
import com.hardcoder.movieapp.ui.home.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.upcomingViewState.collect { viewState ->
                viewState.movieResponse?.let { response ->
                    movieListAdapter.submitList(response.results)
                }
            }
        }
    }

    private fun setUpRv() {
        binding.rvUpcoming.apply {
            setHasFixedSize(true)
            adapter = movieListAdapter
        }
    }

    override fun getFragmentView() = R.layout.fragment_home
}