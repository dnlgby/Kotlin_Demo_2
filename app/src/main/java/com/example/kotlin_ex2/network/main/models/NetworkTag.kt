package com.example.kotlin_ex2.network.main.models

import com.example.kotlin_ex2.data.database.main.entities.DataBaseTag
import com.example.kotlin_ex2.domain.Tag

data class NetworkTag(
    val id: Long,
    val name: String
)


fun List<NetworkTag>.asDomainModel(): List<Tag> {
    return map {
        Tag(
            id = it.id,
            name = it.name
        )
    }
}


fun List<NetworkTag>.asDatabaseModel(): List<DataBaseTag> {
    return map {
        DataBaseTag(
            id = it.id,
            name = it.name
        )
    }
}