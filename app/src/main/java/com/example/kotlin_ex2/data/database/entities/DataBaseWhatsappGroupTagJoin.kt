package com.example.kotlin_ex2.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "groups_tags_join",
    primaryKeys = arrayOf("whatsappGroupId", "tagId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = DataBaseWhatsappGroup::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("whatsappGroupId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DataBaseTag::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("tagId"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class DataBaseWhatsappGroupTagJoin(
    val whatsappGroupId: Long,
    val tagId: Long
)