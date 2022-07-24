package com.hardcoder.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardcoder.movieapp.core.model.MovieResponse
import com.hardcoder.movieapp.data.repository.MovieRepository
import com.hardcoder.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _upcomingViewState = MutableStateFlow(MovieViewState())
    val upcomingViewState = _upcomingViewState.asStateFlow()

    init {
        getMovie()
    }

    private fun getMovie() = viewModelScope.launch {
        when (val response = repository.getUpcomingFromNetwork()) {
            is Resource.Loading -> _upcomingViewState.value =
                upcomingViewState.value.copy(isLoading = true)
            is Resource.Success -> _upcomingViewState.value =
                upcomingViewState.value.copy(movieResponse = response.data)
            is Resource.Error -> _upcomingViewState.value =
                upcomingViewState.value.copy(error = response.message.toString())
        }
    }
}

data class MovieViewState(
    val isLoading: Boolean? = null,
    val movieResponse: MovieResponse? = null,
    val error: String? = null
)