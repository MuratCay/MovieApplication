package com.hardcoder.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardcoder.movieapp.core.model.PopularResponse
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

    private val _popularViewState = MutableStateFlow(MovieViewState())
    val popularViewState = _popularViewState.asStateFlow()

    init {
        getMovie()
    }

    private fun getMovie() = viewModelScope.launch {
        when (val response = repository.getPopularFromNetwork()) {
            is Resource.Loading -> _popularViewState.value =
                popularViewState.value.copy(isLoading = true)
            is Resource.Success -> _popularViewState.value =
                popularViewState.value.copy(popularResponse = response.data)
            is Resource.Error -> _popularViewState.value =
                popularViewState.value.copy(error = response.message.toString())
        }
    }
}

data class MovieViewState(
    val isLoading: Boolean? = null,
    val popularResponse: PopularResponse? = null,
    val error: String? = null
)