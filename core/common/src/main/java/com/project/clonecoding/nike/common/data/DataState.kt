package com.project.clonecoding.nike.common.data

sealed class DataState<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : DataState<T>(data)
    class Error<T>(message: String, data: T? = null) : DataState<T>(data,message)
    class Loading<T>(val isLoading: Boolean = true) : DataState<T>(null,)
}