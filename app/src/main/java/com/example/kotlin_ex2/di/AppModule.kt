package com.example.kotlin_ex2.di

import android.app.Application
import androidx.room.Room
import com.example.kotlin_ex2.common.Constants.Server.SERVER_BASE_URL
import com.example.kotlin_ex2.data.database.AppDataBase
import com.example.kotlin_ex2.data.database.TagDao
import com.example.kotlin_ex2.data.database.WhatsappGroupDao
import com.example.kotlin_ex2.network.WhatsappGroupApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {


    @Singleton
    @Provides
    fun provideWhatsappGroupApi(retrofit: Retrofit): WhatsappGroupApiService {
        return retrofit.create(WhatsappGroupApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(SERVER_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Singleton
    @Provides
    fun provideMainDb(app: Application): AppDataBase {
        return Room
            .databaseBuilder(app, AppDataBase::class.java, AppDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideTagDao(db: AppDataBase): TagDao {
        return db.tagDao()
    }

    @Singleton
    @Provides
    fun provideWhatsappGroupDao(db: AppDataBase): WhatsappGroupDao {
        return db.whatsappGroupDao()
    }

}