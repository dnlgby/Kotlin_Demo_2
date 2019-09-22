package com.example.kotlin_ex2.repositories.main

import androidx.paging.DataSource
import com.example.kotlin_ex2.data.database.main.TagDao
import com.example.kotlin_ex2.data.database.main.WhatsappGroupDao
import com.example.kotlin_ex2.data.database.main.entities.asDomainModel
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.network.main.WhatsappGroupApiService
import com.example.kotlin_ex2.network.main.models.asDatabaseModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    val api: WhatsappGroupApiService,
    val tagDao: TagDao,
    val whatsappGroupDao: WhatsappGroupDao
) {

    fun getAllWhatsappGroups(): DataSource.Factory<Int, WhatsappGroup> {
        return whatsappGroupDao.getAllWhatsappGroups().mapByPage {
            it.asDomainModel()
        }
    }

    suspend fun loadWhatsappGroups() {
        withContext(IO) {
            val results = api.getGroups().results.asDatabaseModel()
            whatsappGroupDao.insertAllWhatsappGroups(results)
        }
    }

    suspend fun loadTags() {
        withContext(IO) {
            val results = api.getTags().results.asDatabaseModel()
            tagDao.insertAllTags(results)
        }
    }


}