package com.example.kotlin_ex2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_ex2.data.database.entities.DataBaseTag
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroup
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroupTagJoin


@Database(
    entities = [
        DataBaseTag::class,
        DataBaseWhatsappGroup::class,
        DataBaseWhatsappGroupTagJoin::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    companion object {
        const val DB_NAME = "AppDataBase"
    }

    abstract fun tagDao(): TagDao
    abstract fun whatsappGroupDao(): WhatsappGroupDao
    abstract fun dataBaseWhatsappGroupTagJoinDao(): DataBaseWhatsappGroupTagJoinDao
}