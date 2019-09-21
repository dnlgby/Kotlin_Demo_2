package com.example.kotlin_ex2.models


data class WhatsappGroup(
    val id: String,
    val name: String,
    val description: String,
    val inviteLink: String,
    val tags: List<Int> = emptyList()
)