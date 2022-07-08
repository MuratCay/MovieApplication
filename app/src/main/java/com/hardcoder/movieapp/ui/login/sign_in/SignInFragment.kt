package com.hardcoder.movieapp.ui.login.sign_in

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.core.base.BaseFragment
import com.hardcoder.movieapp.databinding.FragmentSignInBinding
import com.hardcoder.movieapp.utils.DeviceUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spanSignUpText()
        requestToEditText()
        closeKeyboard()
    }

    private fun spanSignUpText() {
        val signUpSpanText = SpannableString(getString(R.string.sign_up_text))
        val stringSignUp = getString(R.string.string_sign_up)
        val startIndex = getString(R.string.sign_up_text).indexOf(stringSignUp)
        val endIndex = startIndex + stringSignUp.length

        signUpSpanText.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                navigateToFlow()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
                ds.color = ContextCompat.getColor(requireContext(), R.color.white)
            }

        }, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.tvLoginNoAccountWarning.text = signUpSpanText
        binding.tvLoginNoAccountWarning.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun navigateToFlow() {
        findNavController().navigate(R.id.action_SignInFragment_to_signUpFragment)
    }

    private fun requestToEditText() {
        binding.etEmailField.requestFocus()
        DeviceUtils.openKeyboard(requireActivity(), binding.etEmailField)
    }

    private fun closeKeyboard() {
        binding.root.setOnClickListener {
            DeviceUtils.closeKeyboard(requireActivity())
        }
    }

    override fun getFragmentView() = R.layout.fragment_sign_in
}