package com.example.kotlin_ex2.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList.BoundaryCallback
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.network.RequestStatus
import com.example.kotlin_ex2.network.models.ApiResponseListModel
import com.example.kotlin_ex2.network.models.NetworkWhatsappGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class AppBoundaryCallback(
    private val getPage: suspend (pageNumber: Int, query: Set<Long>) -> ApiResponseListModel<NetworkWhatsappGroup>,
    private val insertPage: suspend (items: List<NetworkWhatsappGroup>) -> Unit,
    private val beginPage: Int,
    private val coroutineScope: CoroutineScope
) : BoundaryCallback<WhatsappGroup>() {


    private var currentPage = beginPage
    private var noMoreResults = false
    private lateinit var networkResult: ApiResponseListModel<NetworkWhatsappGroup>
    private var query: MutableSet<Long> = mutableSetOf()

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
                Log.d("XXZZA", "Getting page $pageNumber from network...")

                networkResult = getPage(pageNumber, query)

                Log.d(
                    "XXZZA",
                    "Inserting page $pageNumber to database. (TOTAL OF ${networkResult.results.size} Results)."
                )

                insertPage(networkResult.results)

                _networkStatus.value = RequestStatus.succeed()
                currentPage++
                noMoreResults = networkResult.next.isNullOrBlank()

            } catch (t: Exception) {

                Log.d("XXZZA", t.message)
                Log.d("XXZZA", noMoreResults.toString())
                t.printStackTrace()
                if (noMoreResults) _networkStatus.postValue(RequestStatus.noMore())
                else _networkStatus.postValue(RequestStatus.failed(t.message!!))
            }
        }
    }

    private fun reset() {
        currentPage = beginPage
        noMoreResults = false
    }

    fun setQuery(query: Set<Long>) {
        this.query.clear()
        this.query.addAll(query)
        reset()
    }

    fun retry() = getPageNumber(currentPage)

}