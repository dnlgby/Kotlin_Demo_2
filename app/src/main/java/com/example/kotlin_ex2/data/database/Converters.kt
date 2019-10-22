package com.example.kotlin_ex2.data.database

import androidx.room.TypeConverter

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromSetLongsToString(longs: Set<Long>): String {
        return longs.joinToString()
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToSetLong(string: String): Set<Long> {
        if (string.isBlank()) return emptySet()
        return string.split(',').map {
            it.trim().toLong()
        }.toSet()
    }

}