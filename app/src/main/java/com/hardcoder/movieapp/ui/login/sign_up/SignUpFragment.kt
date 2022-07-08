package com.hardcoder.movieapp.ui.login.sign_up

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
import com.hardcoder.movieapp.databinding.FragmentSignUpBinding
import com.hardcoder.movieapp.utils.DeviceUtils

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignUpBack.setOnClickListener { findNavController().popBackStack() }
        requestToEditText()
        closeKeyboard()
        spanSignUpText()
    }

    private fun spanSignUpText() {
        val signUpSpanText = SpannableString(getString(R.string.sign_in_text))
        val stringSignUp = getString(R.string.string_sign_in)
        val startIndex = getString(R.string.sign_in_text).indexOf(stringSignUp)
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

        binding.tvLoginAlreadyHaveAccountWarning.text = signUpSpanText
        binding.tvLoginAlreadyHaveAccountWarning.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun navigateToFlow() {
        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
    }

    private fun requestToEditText() {
        binding.etSignUpNameField.requestFocus()
        DeviceUtils.openKeyboard(requireActivity(), binding.etSignUpNameField)
    }

    private fun closeKeyboard() {
        binding.root.setOnClickListener {
            DeviceUtils.closeKeyboard(requireActivity())
        }
    }

    override fun getFragmentView() = R.layout.fragment_sign_up
}
