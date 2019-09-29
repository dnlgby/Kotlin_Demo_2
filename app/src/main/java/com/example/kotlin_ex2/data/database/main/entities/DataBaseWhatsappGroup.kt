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


fun DataBaseWhatsappGroup.asDomainModel(): WhatsappGroup {

    return WhatsappGroup(
        id = id,
        name = name,
        description = description,
        inviteLink = inviteLink,
        tags = tags
    )

}


fun List<DataBaseWhatsappGroup>.asDomainModel(): List<WhatsappGroup> {
    return map { it.asDomainModel() }
}