package com.example.kotlin_ex2.repositories

sealed class Action<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null) : Action<T>(data)
    class Loading<T>(data: T? = null) : Action<T>(data)
    class Error(message: String) : Action<Nothing>(null, message)
}