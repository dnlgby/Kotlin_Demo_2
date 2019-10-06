package com.example.kotlin_ex2.network.main.models

import com.example.kotlin_ex2.data.database.main.entities.DataBaseWhatsappGroup
import com.squareup.moshi.Json

data class NetworkWhatsappGroup(
    val id: Long,
    val name: String,
    val description: String,
    @Json(name = "invite_link") val inviteLink: String,
    val tags: List<Int> = emptyList()
)

fun NetworkWhatsappGroup.asDatabaseModel(): DataBaseWhatsappGroup {
    return DataBaseWhatsappGroup(
        id = this.id,
        name = this.name,
        description = this.description,
        inviteLink = this.inviteLink,
        tags = this.tags
    )
}

fun List<NetworkWhatsappGroup>.asDatabaseModel(): List<DataBaseWhatsappGroup> {
    return map { it.asDatabaseModel() }
}
