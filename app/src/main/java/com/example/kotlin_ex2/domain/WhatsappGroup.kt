package com.example.kotlin_ex2.domain

import com.example.kotlin_ex2.network.models.NetworkWhatsappGroup

data class WhatsappGroup(
    val id: Long? = null,
    val name: String,
    val description: String,
    val inviteLink: String,
    val tags: List<Long> = emptyList()
)


fun WhatsappGroup.asNetworkModel(): NetworkWhatsappGroup {
    return NetworkWhatsappGroup(
        id = this.id ?: 0,
        name = this.name,
        description = this.description,
        inviteLink = this.inviteLink,
        tags = this.tags
    )
}

