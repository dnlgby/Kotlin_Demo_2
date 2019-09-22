package com.example.kotlin_ex2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.kotlin_ex2.repositories.main.MainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    companion object {
        const val WHATSAPP_GROUP_PAGE_SIZE = 30
        const val WHATSAPP_GROUP_LIST_MAX_SIZE = 200
        const val WHATSAPP_GROUP_LIST_PLACE_HOLDER = true
    }


    val whatsappGroupsLiveData by lazy {
        mainRepository.getAllWhatsappGroups()
            .toLiveData(
                Config(
                    pageSize = WHATSAPP_GROUP_PAGE_SIZE,
                    enablePlaceholders = WHATSAPP_GROUP_LIST_PLACE_HOLDER,
                    maxSize = WHATSAPP_GROUP_LIST_MAX_SIZE
                )
            )
    }


    fun testb() {
        viewModelScope.launch {
            mainRepository.loadWhatsappGroups()
        }
    }
}