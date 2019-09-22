package com.example.kotlin_ex2.network.main.models

import com.example.kotlin_ex2.data.database.main.entities.DataBaseWhatsappGroup
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.squareup.moshi.Json

data class NetworkWhatsappGroup(
    val id: String,
    val name: String,
    val description: String,
    @Json(name = "invite_link") val inviteLink: String,
    val tags: List<Int> = emptyList()
)


fun List<NetworkWhatsappGroup>.asDomainModel(): List<WhatsappGroup> {
    return map {
        WhatsappGroup(
            id = it.id,
            name = it.name,
            description = it.description,
            inviteLink = it.inviteLink,
            tags = it.tags
        )
    }
}

fun List<NetworkWhatsappGroup>.asDatabaseModel(): List<DataBaseWhatsappGroup> {
    return map {
        DataBaseWhatsappGroup(
            id = it.id,
            name = it.name,
            description = it.description,
            inviteLink = it.inviteLink,
            tags = it.tags
        )
    }
}
