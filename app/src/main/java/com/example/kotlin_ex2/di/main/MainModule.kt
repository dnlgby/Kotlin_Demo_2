package com.example.kotlin_ex2.di.main

import android.app.Application
import androidx.room.Room
import com.example.kotlin_ex2.data.database.main.MainDataBase
import com.example.kotlin_ex2.data.database.main.MainDataBase.Companion.DB_NAME
import com.example.kotlin_ex2.data.database.main.TagDao
import com.example.kotlin_ex2.data.database.main.WhatsappGroupDao
import com.example.kotlin_ex2.network.main.WhatsappGroupApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideWhatsappGroupApi(retrofit: Retrofit): WhatsappGroupApiService {
        return retrofit.create(WhatsappGroupApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideMainDb(app: Application): MainDataBase {
        return Room
            .databaseBuilder(app, MainDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @MainScope
    @Provides
    fun provideTagDao(db: MainDataBase): TagDao {
        return db.tagDao()
    }

    @MainScope
    @Provides
    fun provideWhatsappGroupDao(db: MainDataBase): WhatsappGroupDao {
        return db.WhatsappGroupDao()
    }

}