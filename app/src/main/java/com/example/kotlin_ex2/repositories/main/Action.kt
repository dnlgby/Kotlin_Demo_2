package com.example.kotlin_ex2.repositories.main

sealed class Action<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null) : Action<T>(data)
    class Loading<T>(data: T? = null) : Action<T>(data)
    class Error<T>(message: String, data: T? = null) : Action<T>(data, message)
}