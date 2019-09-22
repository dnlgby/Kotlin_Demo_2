package com.example.kotlin_ex2.data.database.main

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_ex2.data.database.main.entities.DataBaseTag
import com.example.kotlin_ex2.data.database.main.entities.DataBaseWhatsappGroup


@Database(
    entities = [DataBaseTag::class, DataBaseWhatsappGroup::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MainDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "MainDataBase"
    }

    abstract fun tagDao(): TagDao
    abstract fun WhatsappGroupDao(): WhatsappGroupDao

}