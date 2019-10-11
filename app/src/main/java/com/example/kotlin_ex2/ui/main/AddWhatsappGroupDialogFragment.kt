package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import com.example.kotlin_ex2.R
import com.example.kotlin_ex2.common.AppAnimations
import com.example.kotlin_ex2.domain.WhatsappGroup
import dagger.android.support.DaggerDialogFragment
import kotlinx.android.synthetic.main.add_whatsappgroup_dialog_fragment.view.*
import javax.inject.Inject

class AddWhatsappGroupDialogFragment
@Inject constructor(private val callbacks: MainActivity) : DaggerDialogFragment() {


    companion object {
        const val SCALE_DOWN_ANIMATION_DURATION = 300L
        const val SCALE_DOWN_ANIMATION_MIN = 0.5f
        const val SCALE_DOWN_ANIMATION_MAX = 1.0f
    }

    private lateinit var dialogView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_whatsappgroup_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogView = view
        setTagFilterViewItems()
        setListeners()
    }

    override fun onStart() {
        super.onStart()
        val decorView = dialog!!.window!!.decorView
        AppAnimations.ScalingAnimations.scaleDown(
            decorView,
            SCALE_DOWN_ANIMATION_MIN,
            SCALE_DOWN_ANIMATION_MAX,
            SCALE_DOWN_ANIMATION_DURATION
        )
    }

    private fun setTagFilterViewItems() {
//        for (tagDesc in tagsDescriptions)
//            dialogView.main_AddGroupDialogTagFilterView.addTagToggleItem(
//                tagDesc.id,
//                tagDesc.drawableOff,
//                tagDesc.drawableOn
//            )
    }

    private fun setListeners() {
        dialogView.main_AddGroupDialogGroupAddBtn.setOnClickListener {
            val group = validateInput()
            if (group != null) callbacks.groupAdded(group)
        }

        dialogView.main_AddGroupDialogGroupCancelBtn.setOnClickListener {
            dismiss()
        }
    }

    private fun validateInput(): WhatsappGroup? {
        fun validateField(editText: EditText, name: String): String? {
            val value = editText.text.toString().trim()
            if (value.isBlank()) {
                editText.error = "$name most be specified!"
                return null
            }
            return value
        }

        fun validateGroupTags(): List<Long>? {
            val groupTags = dialogView.main_AddGroupDialogTagFilterView.getEnabledItemIds()
            if (groupTags.isEmpty()) {
                val animShake = AnimationUtils.loadAnimation(context, R.anim.shake_animation)
                dialogView.main_AddGroupDialogTagFilterView.startAnimation(animShake)
                return null
            }
            return groupTags
        }

        val groupName = validateField(dialogView.main_AddGroupDlgGroupNameEt, "Group name")
        val groupDescription =
            validateField(dialogView.main_AddGroupDlgGroupDescriptionEt, "Group Description")
        val groupInviteLink =
            validateField(dialogView.main_AddGroupDlgGroupInviteLinkEt, "Group invite link")
        val groupTags = validateGroupTags()

        if (groupName.isNullOrBlank() ||
            groupDescription.isNullOrBlank() ||
            groupInviteLink.isNullOrBlank() ||
            groupTags.isNullOrEmpty()
        )
            return null

        return WhatsappGroup(
            name = groupName,
            description = groupDescription,
            inviteLink = groupInviteLink,
            tags = groupTags
        )
    }

    fun actionInProgress() {
        toggleViews(false)
        dialogView.main_AddGroupDlgProgressBar.visibility = View.VISIBLE
    }

    fun actionFailure() {
        toggleViews(true)
        dialogView.main_AddGroupDlgProgressBar.visibility = View.GONE
    }

    fun actionSucceed() {
        dismiss()
    }

    private fun toggleViews(toggle: Boolean) {
        dialogView.main_AddGroupDlgGroupNameEt.isEnabled = toggle
        dialogView.main_AddGroupDlgGroupDescriptionEt.isEnabled = toggle
        dialogView.main_AddGroupDlgGroupInviteLinkEt.isEnabled = toggle
        dialogView.main_AddGroupDialogTagFilterView.toggleView(toggle)
        dialogView.main_AddGroupDialogGroupAddBtn.isEnabled = toggle
        dialogView.main_AddGroupDialogGroupCancelBtn.isEnabled = toggle
    }

    interface AddWhatsappGroupDialogCallbacks {
        fun groupAdded(group: WhatsappGroup)
    }

}