package com.hardcoder.movieapp.ui.login

import androidx.fragment.app.viewModels
import com.hardcoder.movieapp.core.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<LoginViewModel>()


}