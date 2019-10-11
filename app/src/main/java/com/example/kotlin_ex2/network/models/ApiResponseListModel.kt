package com.example.kotlin_ex2.network.models


data class ApiResponseListModel<T>(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<T>
)