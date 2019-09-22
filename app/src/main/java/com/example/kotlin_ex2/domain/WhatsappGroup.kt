package com.example.kotlin_ex2.domain

data class WhatsappGroup(
    val id: String,
    val name: String,
    val description: String,
    val inviteLink: String,
    val tags: List<Int> = emptyList()
)