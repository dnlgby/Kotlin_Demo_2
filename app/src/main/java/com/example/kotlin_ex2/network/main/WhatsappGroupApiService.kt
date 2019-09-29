package com.example.kotlin_ex2.network.main

import com.example.kotlin_ex2.network.main.models.ApiResponseListModel
import com.example.kotlin_ex2.network.main.models.NetworkTag
import com.example.kotlin_ex2.network.main.models.NetworkWhatsappGroup
import retrofit2.http.GET
import retrofit2.http.Query

interface WhatsappGroupApiService {

    @GET("api/groups/")
    suspend fun getGroups(@Query("page") page: Int): ApiResponseListModel<NetworkWhatsappGroup>


    @GET("api/tags")
    suspend fun getTags(): ApiResponseListModel<NetworkTag>
}