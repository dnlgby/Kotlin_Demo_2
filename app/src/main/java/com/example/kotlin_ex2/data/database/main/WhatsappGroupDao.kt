package com.example.kotlin_ex2.data.database.main

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.kotlin_ex2.data.database.main.entities.DataBaseWhatsappGroup


@Dao
interface WhatsappGroupDao {

    @Query("SELECT * FROM whatsapp_group_table")
    fun getAllWhatsappGroups(): DataSource.Factory<Int, DataBaseWhatsappGroup>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllWhatsappGroups(items: List<DataBaseWhatsappGroup>)
}