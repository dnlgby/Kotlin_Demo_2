package com.example.kotlin_ex2.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroup


@Dao
interface WhatsappGroupDao {

    @Query("SELECT * FROM groups")
    fun getAllWhatsappGroups(): DataSource.Factory<Int, DataBaseWhatsappGroup>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWhatsappGroups(items: List<DataBaseWhatsappGroup>)

    @Query("DELETE FROM groups")
    fun deleteAll()

}