package com.hardcoder.movieapp.ui.login.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardcoder.movieapp.utils.StringUtils
import com.hardcoder.movieapp.utils.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    private val _viewEffect = MutableSharedFlow<SignUpViewEffects>()
    val viewEffect = _viewEffect.asSharedFlow()

    fun checkMissingField(mailText: String, passwordText: String) = viewModelScope.launch {
        if (mailText.isBlank()) {
            _viewEffect.emit(SignUpViewEffects.ShowEmailErrorMessage("Please fill in the blanks"))
        } else if (StringUtils.checkEmailValidation(mailText) == Validation.INVALID) {
            _viewEffect.emit(SignUpViewEffects.ShowEmailErrorMessage("Please enter valid email address"))
        }

        if (passwordText.isBlank()) {
            _viewEffect.emit(SignUpViewEffects.ShowPasswordErrorMessage("Please fill in the blanks"))
        } else if (passwordText.length < 13) {
            _viewEffect.emit(SignUpViewEffects.ShowPasswordErrorMessage("Password length at least 13"))
        }
    }

}

sealed class SignUpViewEffects {
    data class ShowEmailErrorMessage(val errorMessage: String) : SignUpViewEffects()
    data class ShowPasswordErrorMessage(val errorMessage: String) : SignUpViewEffects()
}