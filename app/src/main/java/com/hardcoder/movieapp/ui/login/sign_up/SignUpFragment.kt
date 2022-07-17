package com.hardcoder.movieapp.ui.login.sign_up

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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.hardcoder.movieapp.R
import com.hardcoder.movieapp.databinding.FragmentSignUpBinding
import com.hardcoder.movieapp.ui.base.AbstractLoginFragment

class SignUpFragment : AbstractLoginFragment() {

    val viewModel by viewModels<SignUpViewModel>()
    lateinit var binding: FragmentSignUpBinding
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance()
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        register()
    }

    private fun initViews() {
        binding.btnSignUpBack.setOnClickListener { findNavController().popBackStack() }
        requestToEditText()
        spanSignInText()
        binding.root.setOnClickListener {
            closeKeyboard()
        }
    }

    private fun spanSignInText() {
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
        openKeyboard(binding.etSignUpNameField)
    }

    private fun register() {
        with(binding) {
            btnCreateAccount.setOnClickListener {
                if (etSignUpEmailField.text.isNullOrBlank().not()
                    && etSignUpPasswordField.text.isNullOrBlank().not()
                    && etSignUpNameField.text.isNullOrBlank().not()
                ) {
                    newUserRegister(
                        etSignUpEmailField.text.toString(),
                        etSignUpPasswordField.text.toString()
                    )
                } else {
                    Toast.makeText(requireContext(), "Please fill the blanks", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    private fun newUserRegister(mail: String, sifre: String) {
        auth.createUserWithEmailAndPassword(mail, sifre)
            .addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "User created:" + FirebaseAuth.getInstance().currentUser?.email,
                        Toast.LENGTH_SHORT
                    ).show()
                    sendMail()
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    clearText()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Oops.. Error :" + p0.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun sendMail() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        firebaseUser?.let {
            firebaseUser.sendEmailVerification()
                .addOnCompleteListener { p0 ->
                    if (p0.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "Please check email address and confirm the mail",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Oops.. There is an error when sending confirmation mail:  " + p0.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }

    private fun clearText() {
        with(binding) {
            etSignUpNameField.text?.clear()
            etSignUpEmailField.text?.clear()
            etSignUpPasswordField.text?.clear()
        }
    }
}

