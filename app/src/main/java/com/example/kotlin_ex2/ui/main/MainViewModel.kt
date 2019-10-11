package com.example.kotlin_ex2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.repositories.Action
import com.example.kotlin_ex2.repositories.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mainRepository: Repository) : ViewModel() {

    val whatsappGroupsLiveData = mainRepository.whatsappGroupsLiveData
    val networkStateLiveData = mainRepository.networkStateLiveData
    val getTagsStatusLiveData = mainRepository.getAllTags()

    // Add group status LiveData
    private val _addGroupStatusLiveData = MutableLiveData<Action<Nothing>>()
    val addGroupStatusLiveData: LiveData<Action<Nothing>>
        get() = _addGroupStatusLiveData


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