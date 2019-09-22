package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.kotlin_ex2.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    companion object {
        const val LIST_SPACING = 20
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    lateinit var mainListAdapter: MainRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initUi()
        mainViewModel.testb()
    }

    private fun initViewModel() {
        mainViewModel.whatsappGroupsLiveData.observe(this, Observer {
            mainListAdapter.submitList(it)
            for (i in it) {
                println(i)
            }
        })
    }

    private fun initUi() {
        main_FilterButtonLv.apply {
            setOnClickListener {
                (it as LottieAnimationView).playAnimation()
            }
        }

        main_AddButtonLv.apply {
            setOnClickListener {
                (it as LottieAnimationView).playAnimation()

                val dialog =
                    AddWhatsappGroupDialogFragment.getInstance()
                dialog.show(
                    supportFragmentManager,
                    "TEST"
                )
            }
        }

        mainListAdapter = MainRecyclerAdapter()
        main_groupsRecycler.apply {
            addItemDecoration(
                MainListItemDecoration(LIST_SPACING)
            )
            adapter = mainListAdapter
        }
    }

}