package com.josphatmwania.hoppyhour.common

sealed class Resource<T>(var data: T?, var message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T> : Resource<T>(null, null)
}