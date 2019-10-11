package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.kotlin_ex2.R
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.repositories.Action
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),
    AddWhatsappGroupDialogFragment.AddWhatsappGroupDialogCallbacks {


    companion object {
        const val LIST_SPACING = 20
        const val ADD_WHATSAPP_GROUP_DIALOG_TAG =
            "com.example.kotlin_ex2.ui.main.AddWhatsappGroupDialogFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var addGroupDialog: AddWhatsappGroupDialogFragment
    private lateinit var mainListAdapter: MainRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initUi()
    }

    private fun initViewModel() {

        mainViewModel.whatsappGroupsLiveData.observe(this, Observer {
            mainListAdapter.submitList(it)
        })

        mainViewModel.networkStateLiveData.observe(this, Observer {
            mainListAdapter.setRequestStatus(it)
        })

        mainViewModel.addGroupStatusLiveData.observe(this, Observer {
            when (it) {
                is Action.Loading -> if (addGroupDialog.isVisible) addGroupDialog.actionInProgress()
                is Action.Success -> if (addGroupDialog.isVisible) addGroupDialog.actionSucceed()
                is Action.Error -> {
                    if (addGroupDialog.isVisible) addGroupDialog.actionFailure()
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        mainViewModel.getTagsStatusLiveData.observe(this, Observer {

            for (tagItem in it)
                Log.d("ZOXOZ", tagItem.toString())

//                is Action.Error -> {
//                    if (addGroupDialog.isVisible) addGroupDialog.actionFailure()
//                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
//                }
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
                addGroupDialog.show(
                    supportFragmentManager,
                    ADD_WHATSAPP_GROUP_DIALOG_TAG
                )
            }
        }

        mainListAdapter = MainRecyclerAdapter(retryCallback = { mainViewModel.retryFetch() })
        main_groupsRecycler.apply {
            addItemDecoration(
                MainListItemDecoration(LIST_SPACING)
            )
            adapter = mainListAdapter
        }
    }

    override fun groupAdded(group: WhatsappGroup) {
        mainViewModel.addGroup(group)
    }

}