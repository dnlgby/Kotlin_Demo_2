package com.example.kotlin_ex2.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.kotlin_ex2.data.database.TagDao
import com.example.kotlin_ex2.data.database.WhatsappGroupDao
import com.example.kotlin_ex2.data.database.entities.asDomainModel
import com.example.kotlin_ex2.domain.Tag
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.domain.asNetworkModel
import com.example.kotlin_ex2.network.WhatsappGroupApiService
import com.example.kotlin_ex2.network.models.asDatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: WhatsappGroupApiService,
    private val tagDao: TagDao,
    private val whatsappGroupDao: WhatsappGroupDao
) {

    private val repositoryJob = Job()
    private val repositoryScope = CoroutineScope(Main + repositoryJob)

    companion object {
        const val WHATSAPP_GROUP_PAGE_SIZE = 6
        const val WHATSAPP_GROUP_LIST_MAX_SIZE = 200
        const val WHATSAPP_GROUP_LIST_PLACE_HOLDER = true
        const val WHATSAPP_GROUP_LIST_BEGIN_PAGE = 1
    }

    private val boundaryCallback =
        AppBoundaryCallback(
            { api.getGroups(it) },
            { whatsappGroupDao.insertAllWhatsappGroups(it) },
            WHATSAPP_GROUP_LIST_BEGIN_PAGE,
            repositoryScope
        )

    val networkStateLiveData = boundaryCallback.requestStatus

    val whatsappGroupsLiveData by lazy {
        whatsappGroupDao.getAllWhatsappGroups().map { it.asDomainModel() }
            .toLiveData(
                Config(
                    pageSize = WHATSAPP_GROUP_PAGE_SIZE,
                    maxSize = WHATSAPP_GROUP_LIST_MAX_SIZE,
                    enablePlaceholders = WHATSAPP_GROUP_LIST_PLACE_HOLDER
                ),
                boundaryCallback = boundaryCallback
            )
    }

    fun retry() {
        boundaryCallback.retry()
    }

    suspend fun addGroup(group: WhatsappGroup) {
        api.addGroup(group.asNetworkModel())
    }

    fun getAllTags(): LiveData<List<Tag>> {
        return Transformations.map(tagDao.getAllTags()) {
            it.asDomainModel()
        }
    }

    fun loadTags() {
        repositoryScope.launch {
            try {
                val results = api.getTags().results.asDatabaseModel()
                tagDao.insertAllTags(results)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun clear() {
        repositoryJob.cancel()
    }

}