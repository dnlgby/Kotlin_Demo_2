package com.example.kotlin_ex2.network.main

import com.example.kotlin_ex2.network.main.models.ApiResponseListModel
import com.example.kotlin_ex2.network.main.models.NetworkTag
import com.example.kotlin_ex2.network.main.models.NetworkWhatsappGroup
import retrofit2.http.GET

interface WhatsappGroupApiService {

    @GET("api/groups/")
    suspend fun getGroups(): ApiResponseListModel<NetworkWhatsappGroup>

    @GET("api/tags")
    suspend fun getTags(): ApiResponseListModel<NetworkTag>
}