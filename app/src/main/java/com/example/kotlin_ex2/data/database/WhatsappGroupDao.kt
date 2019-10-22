package com.example.kotlin_ex2.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroup


@Dao
interface WhatsappGroupDao {

    @Query("SELECT * FROM whatsapp_group_table")
    fun test(): LiveData<List<DataBaseWhatsappGroup>>

    @Query("SELECT * FROM whatsapp_group_table")
    fun getAllWhatsappGroups(): DataSource.Factory<Int, DataBaseWhatsappGroup>

    @Query("SELECT * FROM whatsapp_group_table where tags Like (:tagId)")
    fun getWhatsappGroupsByTags(tagId: Long): DataSource.Factory<Int, DataBaseWhatsappGroup>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllWhatsappGroups(items: List<DataBaseWhatsappGroup>)

    @Query("DELETE FROM whatsapp_group_table")
    fun deleteAll()

}