package com.example.kotlin_ex2.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlin_ex2.domain.Tag


@Entity(tableName = "tags")
data class DataBaseTag(
    @PrimaryKey val id: Long,
    val name: String
)

fun List<DataBaseTag>.asDomainModel(): List<Tag> {
    return map {
        Tag(
            id = it.id,
            name = it.name
        )
    }
}
