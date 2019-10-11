package com.example.kotlin_ex2.network

import com.example.kotlin_ex2.network.models.ApiResponseListModel
import com.example.kotlin_ex2.network.models.NetworkTag
import com.example.kotlin_ex2.network.models.NetworkWhatsappGroup
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WhatsappGroupApiService {

    @GET("api/groups/")
    suspend fun getGroups(@Query("page") page: Int): ApiResponseListModel<NetworkWhatsappGroup>

    @POST("api/groups/")
    suspend fun addGroup(@Body whatsappGroup: NetworkWhatsappGroup)

    @GET("api/tags")
    suspend fun getTags(): ApiResponseListModel<NetworkTag>
}