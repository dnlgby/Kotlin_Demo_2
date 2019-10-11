package com.example.kotlin_ex2.network.models

import com.example.kotlin_ex2.data.database.entities.DataBaseTag

data class NetworkTag(
    val id: Long,
    val name: String
)

fun List<NetworkTag>.asDatabaseModel(): List<DataBaseTag> {
    return map {
        DataBaseTag(
            id = it.id,
            name = it.name
        )
    }
}