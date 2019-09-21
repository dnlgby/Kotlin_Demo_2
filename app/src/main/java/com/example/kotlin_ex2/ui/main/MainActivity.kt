package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import androidx.activity.viewModels

import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_ex2.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        mainViewModel.testa()
    }

}