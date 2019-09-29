package com.example.kotlin_ex2.repositories.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList.BoundaryCallback
import com.example.kotlin_ex2.data.database.main.entities.DataBaseWhatsappGroup
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.network.main.RequestStatus
import com.example.kotlin_ex2.network.main.models.ApiResponseListModel
import com.example.kotlin_ex2.network.main.models.NetworkWhatsappGroup
import com.example.kotlin_ex2.network.main.models.asDatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppBoundaryCallback(
    private val getPage: suspend (pageNumber: Int) -> ApiResponseListModel<NetworkWhatsappGroup>,
    private val insertPage: suspend (items: List<DataBaseWhatsappGroup>) -> Unit,
    beginPage: Int,
    private val coroutineScope: CoroutineScope
) : BoundaryCallback<WhatsappGroup>() {

    private var currentPage = beginPage
    private var noMoreResults = false
    private lateinit var networkResult: ApiResponseListModel<NetworkWhatsappGroup>
    private val _networkStatus by lazy {
        MutableLiveData<RequestStatus>()
    }
    val requestStatus: LiveData<RequestStatus>
        get() = _networkStatus

    override fun onZeroItemsLoaded() = getPageNumber(currentPage)

    override fun onItemAtEndLoaded(itemAtEnd: WhatsappGroup) = getPageNumber(currentPage)

    private fun getPageNumber(pageNumber: Int) {
        coroutineScope.launch {
            _networkStatus.postValue(RequestStatus.fetching())
            try {
                networkResult = getPage(pageNumber)
                insertPage(networkResult.results.asDatabaseModel())
                _networkStatus.value = RequestStatus.succeed()
                currentPage++
                noMoreResults = networkResult.next.isNullOrBlank()
            } catch (t: Throwable) {
                if (noMoreResults) _networkStatus.postValue(RequestStatus.noMore())
                else _networkStatus.postValue(RequestStatus.failed(t.message!!))
            }
        }
    }

    fun retry() = getPageNumber(currentPage)
}