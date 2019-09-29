package com.example.kotlin_ex2.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.kotlin_ex2.repositories.main.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject


class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val whatsappGroupsLiveData = mainRepository.whatsappGroupsLiveData
    val networkStateLiveData = mainRepository.networkStateLiveData

    val k = liveData<Int>(IO) {
        MutableLiveData<Int>()

    }

    fun retryFetch() {
        viewModelScope
        mainRepository.retry()
    }

    override fun onCleared() {
        super.onCleared()
        mainRepository.clear()
    }

}