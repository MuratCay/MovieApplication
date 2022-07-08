package com.hardcoder.movieapp.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.core.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun navigateToFlow() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewEffect.collect {
                when (it) {
                    is SplashViewEffect.PassToLoginPage -> navigateToFlow()
                }
            }
        }
    }

}