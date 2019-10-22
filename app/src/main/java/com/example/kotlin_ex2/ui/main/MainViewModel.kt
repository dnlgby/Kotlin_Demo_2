package com.example.kotlin_ex2.ui.main

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.repositories.Action
import com.example.kotlin_ex2.repositories.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mainRepository: Repository) : ViewModel() {


    val whatsappGroupsLiveData = MediatorLiveData<PagedList<WhatsappGroup>>()
    private var currentGroupSourceLiveData: LiveData<PagedList<WhatsappGroup>>? = null

    // In the beginning - Listen to the global source (This is a TEST ofc).
    init {
        //setQuery(mutableSetOf(19))
        //whatsappGroupsLiveData.addSource(mainRepository.whatsappGroupsLiveData) {
        //    whatsappGroupsLiveData.value = it
        // }
    }

    //val whatsappGroupsLiveData = mainRepository.whatsappGroupsLiveData
    val networkStateLiveData = mainRepository.networkStateLiveData
    val getTagsStatusLiveData = mainRepository.getAllTags()
    val test = mainRepository.test

    // Add group status LiveData
    private val _addGroupStatusLiveData = MutableLiveData<Action<Nothing>>()
    val addGroupStatusLiveData: LiveData<Action<Nothing>>
        get() = _addGroupStatusLiveData


    fun setQuery(query: Set<Long>) {
        if (currentGroupSourceLiveData != null) whatsappGroupsLiveData.removeSource(
            currentGroupSourceLiveData!!
        )
        currentGroupSourceLiveData = mainRepository.setQuery(query)
        whatsappGroupsLiveData.addSource(currentGroupSourceLiveData!!) {
            whatsappGroupsLiveData.value = it
        }
    }

    fun addGroup(group: WhatsappGroup) {
        viewModelScope.launch {
            try {
                _addGroupStatusLiveData.value = Action.Loading()
                mainRepository.addGroup(group)
                _addGroupStatusLiveData.value = Action.Success()
            } catch (t: Throwable) {
                _addGroupStatusLiveData.value = Action.Error(message = t.message!!)
            }
        }
    }

    fun retryFetch() {
        mainRepository.retry()
    }

    override fun onCleared() {
        super.onCleared()
        mainRepository.clear()
    }

}