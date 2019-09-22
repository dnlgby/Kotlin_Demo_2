package com.example.kotlin_ex2.data.database.main.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlin_ex2.domain.WhatsappGroup

@Entity(tableName = "whatsapp_group_table")
data class DataBaseWhatsappGroup(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val inviteLink: String,
    val tags: List<Int> = emptyList()
)

fun List<DataBaseWhatsappGroup>.asDomainModel(): List<WhatsappGroup> {
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

fun List<DataBaseWhatsappGroup>.asDataBaseModel(): List<DataBaseWhatsappGroup> {
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