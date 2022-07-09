package com.hardcoder.movieapp.ui.login.sign_in

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.databinding.FragmentSignInBinding
import com.hardcoder.movieapp.ui.base.AbstractLoginFragment
import com.hardcoder.movieapp.utils.StringUtils
import com.hardcoder.movieapp.utils.Validation
import com.hardcoder.movieapp.utils.gone
import com.hardcoder.movieapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : AbstractLoginFragment() {

    private val viewModel by viewModels<SignInViewModel>()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        addTextChanged()
    }

    private fun addTextChanged() {
        binding.etPasswordField.addTextChangedListener { passwordEditable ->
            passwordEditable?.let { editText ->
                if (editText.length >= 13 || editText.isNotBlank())
                    binding.tvPasswordError.gone()
            }
        }

        binding.etEmailField.addTextChangedListener { emailEditable ->
            emailEditable?.let {
                if (StringUtils.checkEmailValidation(it.toString()) == Validation.VALID) {
                    binding.tvEmailError.gone()
                }
            }

        }
    }

    private fun initViews() {
        spanSignUpText()
        requestToEditText()
        binding.root.setOnClickListener {
            closeKeyboard()
        }
        checkMissingField()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewEffect.collect { viewEffect ->
                when (viewEffect) {
                    is SignUpViewEffects.ShowEmailErrorMessage -> showEmailError(viewEffect.errorMessage)
                    is SignUpViewEffects.ShowPasswordErrorMessage -> showPasswordError(viewEffect.errorMessage)
                }
            }
        }
    }

    private fun showPasswordError(errorMessage: String) {
        with(binding.tvPasswordError) {
            text = errorMessage
            visible()
        }

    }

    private fun showEmailError(errorMessage: String) {
        with(binding.tvEmailError) {
            text = errorMessage
            visible()
        }
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
        openKeyboard(binding.etEmailField)
    }

    private fun checkMissingField() {
        binding.btnLogin.setOnClickListener {
            with(binding) {
                viewModel.checkMissingField(
                    etEmailField.text.toString(),
                    etPasswordField.text.toString()
                )
            }

        }
    }

}