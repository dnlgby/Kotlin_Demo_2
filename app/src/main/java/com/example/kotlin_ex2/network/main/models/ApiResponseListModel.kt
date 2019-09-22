package com.example.kotlin_ex2.network.main.models


data class ApiResponseListModel<T>(
    val count: Int?,
    val next: Int?,
    val previous: Int?,
    val results: List<T>
)