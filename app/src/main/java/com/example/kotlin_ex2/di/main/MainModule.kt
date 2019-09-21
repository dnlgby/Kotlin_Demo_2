package com.example.kotlin_ex2.di.main

import com.example.kotlin_ex2.network.WhatsappGroupApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideWhatsappGroupApi(retrofit: Retrofit): WhatsappGroupApiService{
        return retrofit.create(WhatsappGroupApiService::class.java)
    }
}