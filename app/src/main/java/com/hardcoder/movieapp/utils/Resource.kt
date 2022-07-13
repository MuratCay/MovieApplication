package com.hardcoder.movieapp.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class Resource<out T>(val status: Status, val data: T?, val message: Throwable?) {
    class Loading<T> : Resource<T>(Status.LOADING, null, null)
    class Success<T>(data: T?) : Resource<T>(Status.SUCCESS, data, null)
    class Error<T>(exception: Throwable?) : Resource<T>(Status.ERROR, null, exception)
}