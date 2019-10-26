package com.example.kotlin_ex2.data.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroup
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroupTagJoin

@Dao
interface DataBaseWhatsappGroupTagJoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataBaseWhatsappGroupTagJoin: DataBaseWhatsappGroupTagJoin)

    @Query(
        """
           SELECT * FROM groups
           INNER JOIN groups_tags_join
           ON groups.id=groups_tags_join.whatsappGroupId
           WHERE groups_tags_join.tagId IN (:tagsIds)
           """
    )
    fun getGroupsByTags(tagsIds: Set<Long>): DataSource.Factory<Int, DataBaseWhatsappGroup>


    @Query("DELETE FROM groups_tags_join")
    fun deleteAll()
}