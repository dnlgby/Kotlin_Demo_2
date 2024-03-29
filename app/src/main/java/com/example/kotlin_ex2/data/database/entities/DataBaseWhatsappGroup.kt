package com.example.kotlin_ex2.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlin_ex2.domain.WhatsappGroup

@Entity(tableName = "groups")
data class DataBaseWhatsappGroup(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val inviteLink: String,
    val tags: Set<Long> = emptySet()
)

fun DataBaseWhatsappGroup.asDomainModel(): WhatsappGroup {
    return WhatsappGroup(
        id = id,
        name = name,
        description = description,
        inviteLink = inviteLink,
        tags = tags
    )
}