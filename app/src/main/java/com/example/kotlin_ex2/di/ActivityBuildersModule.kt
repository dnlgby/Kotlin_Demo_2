package com.example.kotlin_ex2.di


import com.example.kotlin_ex2.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelsModule::class,
            DialogFragmentBuildersModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}