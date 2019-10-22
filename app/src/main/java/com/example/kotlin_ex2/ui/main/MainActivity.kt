package com.example.kotlin_ex2.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_ex2.R
import com.example.kotlin_ex2.common.AppAnimations.Utils.animateImageView
import com.example.kotlin_ex2.common.Constants.Tags.getTagDescription
import com.example.kotlin_ex2.domain.WhatsappGroup
import com.example.kotlin_ex2.repositories.Action
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(),
    AddWhatsappGroupDialogFragment.AddWhatsappGroupDialogCallbacks,
    FilterDialogFragment.FilterDialogCallBacks {



    companion object {
        const val LIST_SPACING = 20
        const val FILTER_GROUP_DIALOG_TAG =
            "com.example.kotlin_ex2.ui.main.FilterDialogFragment"
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
    @Inject
    lateinit var filterGroupDialog: FilterDialogFragment
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

        mainViewModel.getTagsStatusLiveData.observe(this, Observer { tags ->
            tags.forEach { tagItem ->
                val tagDescription = getTagDescription(tagItem.id)
                addGroupDialog.addTagDescription(tagDescription)
                filterGroupDialog.addTagDescription(tagDescription)
            }
        })


        mainViewModel.test.observe(this, Observer {
            it.forEach { v ->
                Log.d("CHECKX", v.toString())
            }
        })
    }


    private fun initUi() {
        main_FilterButtonLv.apply {
            setOnClickListener {
                animateImageView(context, this, R.animator.icon_property_animator)
                filterGroupDialog.show(
                    supportFragmentManager,
                    FILTER_GROUP_DIALOG_TAG
                )
            }
        }

        main_AddButtonIv.apply {
            setOnClickListener {
                animateImageView(context, this, R.animator.icon_property_animator)
                addGroupDialog.show(
                    supportFragmentManager,
                    ADD_WHATSAPP_GROUP_DIALOG_TAG
                )
            }
        }

        mainListAdapter = MainRecyclerAdapter(
            retryCallback = { mainViewModel.retryFetch() },
            mainViewItemClickListener = MainRecyclerAdapter.MainViewItemClickListener { group ->
                openGroupInvitationLink(group)
            })

        main_groupsRecycler.apply {
            addItemDecoration(
                MainListItemDecoration(LIST_SPACING)
            )
            adapter = mainListAdapter
        }
    }

    override fun tagsStateChanged(enabledItemIds: Set<Long>) {
        Log.d("ZAINXXX", "STATE CHANGED $enabledItemIds")
        mainViewModel.setQuery(enabledItemIds)
    }

    override fun groupAdded(group: WhatsappGroup) {
        mainViewModel.addGroup(group)
    }

    private fun openGroupInvitationLink(group: WhatsappGroup) {
        var url = group.inviteLink
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://$url"
        }
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}