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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.databinding.FragmentSignInBinding
import com.hardcoder.movieapp.ui.base.AbstractLoginFragment
import com.hardcoder.movieapp.utils.StringUtils
import com.hardcoder.movieapp.utils.Validation
import com.hardcoder.movieapp.utils.gone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : AbstractLoginFragment() {

    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    private lateinit var auth: FirebaseAuth
    private val viewModel by viewModels<SignInViewModel>()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addTextChanged()
        login()
        initAuthStateListener()
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

    private fun login() {
        with(binding) {
            btnLogin.setOnClickListener {
                if (etEmailField.text.isNullOrBlank().not() && etPasswordField.text.isNullOrBlank()
                        .not()
                ) {
                    auth
                        .signInWithEmailAndPassword(
                            etEmailField.text.toString(),
                            etPasswordField.text.toString()
                        )
                        .addOnCompleteListener { p0 ->
                            if (p0.isSuccessful) {
                                FirebaseAuth.getInstance().signOut()
                                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Oops.. Error when try to login to account: " + p0.exception?.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(requireContext(), "Please fill the blanks", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun initAuthStateListener() {
        mAuthStateListener = FirebaseAuth.AuthStateListener { p0 ->
            val user = p0.currentUser
            user?.let { currentUser ->
                if (currentUser.isEmailVerified) {
                    Toast.makeText(
                        requireContext(),
                        "The email address confirmed. You will redirect.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Oops.. Please confirm the mail message",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(mAuthStateListener)
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener { mAuthStateListener }
    }
}