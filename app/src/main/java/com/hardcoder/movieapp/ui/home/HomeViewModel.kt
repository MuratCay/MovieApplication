package com.hardcoder.movieapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardcoder.movieapp.core.model.PopularList
import com.hardcoder.movieapp.core.model.PopularResponse
import com.hardcoder.movieapp.data.repository.MovieRepository
import com.hardcoder.movieapp.utils.Resource
import com.hardcoder.movieapp.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow(MovieViewState())
    val movie = _movie.asStateFlow()

    init {
        getMovie()
    }

    private fun getMovie() = viewModelScope.launch {
        repository.getPopularFromNetwork().onEach { resource ->
            when(resource.status){
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    _movie.value = movie.value.copy(movieList = resource.data)
                }

                Status.ERROR -> {
                    Log.e("TAG", resource.message.toString() )
                }
            }
        }
//            .onStart { _movie.value = movie.value.copy(Resource.Loading()) }
//            .catch { message -> _movie.postValue(Resource.Error(message)) }
//            .collect { _movie.postValue(it) }
    }
}

data class MovieViewState(
    val isLoading: Boolean? = null,
    val movieList: PopularResponse? = null,
    val error: String? = null
)