package com.example.kotlin_ex2

import com.example.kotlin_ex2.di.DaggerAppComponent
import com.example.kotlin_ex2.repositories.Repository
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    @Inject
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        //Load updated tag list at every app startup.
        repository.loadTags()
    }
}