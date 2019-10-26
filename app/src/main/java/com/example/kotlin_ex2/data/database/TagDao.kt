package com.example.kotlin_ex2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_ex2.data.database.entities.DataBaseTag

@Dao
interface TagDao {

    @Query("SELECT * FROM tags")
    fun getAllTags(): LiveData<List<DataBaseTag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTags(tags: List<DataBaseTag>)

    @Query("DELETE FROM tags")
    fun deleteAll()
}