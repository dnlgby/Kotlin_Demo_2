package com.example.kotlin_ex2.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.example.kotlin_ex2.data.database.entities.DataBaseWhatsappGroupTagJoin
import com.example.kotlin_ex2.data.database.entities.asDomainModel
import com.example.kotlin_ex2.domain.Tag
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.network.models.NetworkTag
import com.example.kotlin_ex2.network.models.NetworkWhatsappGroup
import com.example.kotlin_ex2.network.models.asDatabaseModel
import javax.inject.Inject


class DatabaseLayer @Inject constructor(
    private val tagDao: TagDao,
    private val whatsappGroupDao: WhatsappGroupDao,
    private val dataBaseWhatsappGroupTagJoinDao: DataBaseWhatsappGroupTagJoinDao
) {

    suspend fun insertAllWhatsappGroups(items: List<NetworkWhatsappGroup>) {
        val dbModelItems = items.asDatabaseModel()
        whatsappGroupDao.insertAllWhatsappGroups(dbModelItems)
        dbModelItems.forEach { group ->
            group.tags.forEach { tagId ->
                val curJoinItem = DataBaseWhatsappGroupTagJoin(group.id, tagId)
                dataBaseWhatsappGroupTagJoinDao.insert(curJoinItem)
            }
        }
    }

    fun getGroupsByTags(tagsIds: Set<Long>): DataSource.Factory<Int, WhatsappGroup> {
        return dataBaseWhatsappGroupTagJoinDao.getGroupsByTags(tagsIds).map {
            it.asDomainModel()
        }
    }

    fun getAllWhatsappGroups(): DataSource.Factory<Int, WhatsappGroup> {
        return whatsappGroupDao.getAllWhatsappGroups().map { it.asDomainModel() }
    }

    fun getAllTags(): LiveData<List<Tag>> {
        return Transformations.map(tagDao.getAllTags()) {
            it.asDomainModel()
        }
    }

    suspend fun insertAllTags(tags: List<NetworkTag>) {
        tagDao.insertAllTags(tags.asDatabaseModel())
    }

    private fun deleteAllGroups() {
        whatsappGroupDao.deleteAll()
    }

    private fun deleteAllTags() {
        tagDao.deleteAll()
    }

    fun deleteDataBaseContent() {
        dataBaseWhatsappGroupTagJoinDao.deleteAll()
    }
}
