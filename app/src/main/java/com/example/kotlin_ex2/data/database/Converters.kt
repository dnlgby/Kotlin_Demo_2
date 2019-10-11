package com.example.kotlin_ex2.data.database

import androidx.room.TypeConverter

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromListLongsToString(longs: List<Long>): String {
        return longs.joinToString()
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToListLong(string: String): List<Long> {
        if (string.isBlank()) return emptyList()
        return string.split(',').map {
            it.trim().toLong()
        }
    }

}