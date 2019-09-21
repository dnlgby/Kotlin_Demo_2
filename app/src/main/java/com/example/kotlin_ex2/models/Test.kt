package com.example.kotlin_ex2.models

data class Test(
    val count: Long,
    val next: Long?,
    val previous: Long?,
    val results: List<Tag>
)