package com.example.kotlin_ex2.data.database.main

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.kotlin_ex2.data.database.main.entities.DataBaseTag

@Dao
interface TagDao {

    @Query("SELECT * FROM tag_table")
    suspend fun getAllTags(): List<DataBaseTag>

    @Insert(onConflict = REPLACE)
    suspend fun insertAllTags(tags: List<DataBaseTag>)
}