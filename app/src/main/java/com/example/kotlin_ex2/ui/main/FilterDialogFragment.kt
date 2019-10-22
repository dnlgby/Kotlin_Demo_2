package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_ex2.R
import com.example.kotlin_ex2.common.Constants.Tags.TagDescription
import com.example.kotlin_ex2.common.Utils
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.filter_dialog_fragment.view.*
import javax.inject.Inject

class FilterDialogFragment @Inject constructor(private val callbacks: MainActivity) :
    DaggerDialogFragment() {

    companion object {
        const val DIALOG_Y_POSITION = 50
    }

    private lateinit var dialogView: View
    private var tagsDescriptions = mutableSetOf<TagDescription>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogView = view
        initTagFilterView()
    }

    fun addTagDescription(tagDescription: TagDescription) {
        tagsDescriptions.add(tagDescription)
    }

    private fun initTagFilterView() {
        dialogView.main_FilterDialogTagFilterView.setStateChangedListener {
            callbacks.tagsStateChanged(
                dialogView.main_FilterDialogTagFilterView.getEnabledItemIds()
            )
        }
        setTagFilterViewItems()
    }

    private fun setTagFilterViewItems() {
        for (tagDesc in tagsDescriptions)
            dialogView.main_FilterDialogTagFilterView.addTagToggleItem(
                tagDesc.id,
                tagDesc.drawableOff,
                tagDesc.drawableOn
            )
    }

    override fun onResume() {
        super.onResume()
        setDialogPosition()
    }

    private fun setDialogPosition() {
        val window = dialog!!.window!!
        window.setGravity(Gravity.TOP or Gravity.LEFT)
        val params = window.attributes
        params.y = Utils.Screen.dpToPx(context!!, DIALOG_Y_POSITION)
        window.attributes = params
    }

    interface FilterDialogCallBacks {
        fun tagsStateChanged(enabledItemIds: Set<Long>)
    }

}