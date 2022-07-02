package com.hardcoder.movieapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    private var _viewEffect = MutableSharedFlow<SplashViewEffect>()
    val viewEffect = _viewEffect.asSharedFlow()

    init {
        createSplashDuration()
    }

    private fun createSplashDuration() {
        viewModelScope.launch {
            delay(5000)
            _viewEffect.emit(SplashViewEffect.PassToLoginPage)
        }
    }
}

sealed class SplashViewEffect {
    object PassToLoginPage : SplashViewEffect()
}