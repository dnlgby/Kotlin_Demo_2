package com.example.kotlin_ex2.network

import com.example.kotlin_ex2.models.ApiResponseModel
import com.example.kotlin_ex2.models.Test
import com.example.kotlin_ex2.models.WhatsappGroup
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface WhatsappGroupApiService {

    @GET("api/groups/")
    suspend fun getGroups(): Deferred<ApiResponseModel<WhatsappGroup>>

    @GET("api/tags")
    suspend fun getTags(): Test
}