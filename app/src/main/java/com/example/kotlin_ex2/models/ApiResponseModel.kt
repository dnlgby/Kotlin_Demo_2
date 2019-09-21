package com.example.kotlin_ex2.models


data class ApiResponseModel<T>(
    val count: Int,
    val next: Int,
    val previous: Int,
    val result: List<T>
)