package com.example.kotlin_ex2.data.database.main

import androidx.room.TypeConverter

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromListIntegersToString(integers: List<Int>): String {
        return integers.joinToString()
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToListIntegers(string: String): List<Int> {
        if (string.isBlank()) return emptyList()
        return string.split(',').map {
            it.trim().toInt()
        }
    }

}