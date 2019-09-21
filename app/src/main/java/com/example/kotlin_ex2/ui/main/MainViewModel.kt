package com.example.kotlin_ex2.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_ex2.network.WhatsappGroupApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(val test: WhatsappGroupApiService): ViewModel(){

    fun testa(){
        viewModelScope.launch {
            withContext(IO){
                val res = test.getTags()
                withContext(Main)
                {
                    for (i in res.results){

                        println(i)
                    }
                }

            }
        }
    }
}