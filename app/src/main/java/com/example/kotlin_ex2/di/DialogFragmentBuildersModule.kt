package com.example.kotlin_ex2.di

import com.example.kotlin_ex2.ui.main.AddWhatsappGroupDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class DialogFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAddWhatsappGroupDialogFragment(): AddWhatsappGroupDialogFragment
}