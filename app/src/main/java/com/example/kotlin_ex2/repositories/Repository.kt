package com.example.kotlin_ex2.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.kotlin_ex2.data.database.DatabaseLayer
import com.example.kotlin_ex2.domain.Tag
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.domain.asNetworkModel
import com.example.kotlin_ex2.network.WhatsappGroupApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: WhatsappGroupApiService,
    private val database: DatabaseLayer
) {

    companion object {
        private val WHATSAPP_GROUP_PAGE_SIZE = 10
        private const val WHATSAPP_GROUP_LIST_MAX_SIZE = 200
        private const val WHATSAPP_GROUP_LIST_PLACE_HOLDER = true
        private const val WHATSAPP_GROUP_LIST_BEGIN_PAGE = 1

        private val repositoryJob = Job()
        private val repositoryScope = CoroutineScope(Main + repositoryJob)

        private val pagingConfig = Config(
            pageSize = WHATSAPP_GROUP_PAGE_SIZE,
            maxSize = WHATSAPP_GROUP_LIST_MAX_SIZE,
            enablePlaceholders = WHATSAPP_GROUP_LIST_PLACE_HOLDER
        )
    }

    // Network bound resource ballback
    private val boundaryCallback =
        AppBoundaryCallback(
            { PAGE, QUERY -> api.getGroups(PAGE, QUERY) },
            { database.insertAllWhatsappGroups(it) },
            WHATSAPP_GROUP_LIST_BEGIN_PAGE,
            repositoryScope
        )

    // All groups
    val allGroupsLiveData = database.getAllWhatsappGroups()
        .toLiveData(
            config = pagingConfig,
            boundaryCallback = boundaryCallback
        )

    // Network status
    val networkStateLiveData = boundaryCallback.requestStatus

    // Get filtered data LiveData
    fun setQuery(query: Set<Long>): LiveData<PagedList<WhatsappGroup>> {
        boundaryCallback.setQuery(query)
        return database.getGroupsByTags(query)
            .toLiveData(
                pagingConfig,
                boundaryCallback = boundaryCallback
            )
    }

    fun retry() = boundaryCallback.retry()

    suspend fun addGroup(group: WhatsappGroup) {
        api.addGroup(group.asNetworkModel())
    }

    fun getAllTags(): LiveData<List<Tag>> {
        return database.getAllTags()
    }

    fun loadTags() {
        repositoryScope.launch {
            try {
                val results = api.getTags().results
                database.insertAllTags(results)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

    fun clear() {
        repositoryJob.cancel()
        database.deleteDataBaseContent()
    }

}