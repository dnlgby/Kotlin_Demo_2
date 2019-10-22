package com.example.kotlin_ex2.ui.main.views

import androidx.databinding.BindingAdapter
import com.example.kotlin_ex2.common.Constants.Tags.getTagDescription


@BindingAdapter("groupTagsAdapter")
fun TagsView.setTagsValue(groupTags: Set<Long>?) {
    clear()
    groupTags?.forEach { tagId ->
        addTagView(getTagDescription(tagId).drawableOn)
    }
}
