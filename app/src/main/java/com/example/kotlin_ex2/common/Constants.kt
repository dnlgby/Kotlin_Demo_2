package com.example.kotlin_ex2.common

import com.example.kotlin_ex2.R
import com.example.kotlin_ex2.domain.Tag

object Constants {
    object Server {
        const val SERVER_BASE_URL = "http://ec2-3-15-150-102.us-east-2.compute.amazonaws.com/"
    }

    object Tags {
        data class TagDescription(
            val id: Long,
            val name: String,
            val drawableOn: Int,
            val drawableOff: Int
        )

        private val TAGS_MAP = listOf(
            TagDescription(1L, "dating", R.drawable.icon_dating_ena, R.drawable.icon_dating_dis),
            TagDescription(2L, "family", R.drawable.icon_family_ena, R.drawable.icon_family_dis),
            TagDescription(3L, "food", R.drawable.icon_food_ena, R.drawable.icon_food_dis),
            TagDescription(4L, "funny", R.drawable.icon_funny_ena, R.drawable.icon_funny_dis),
            TagDescription(
                5L,
                "parenting",
                R.drawable.icon_parenting_ena,
                R.drawable.icon_parenting_dis
            ),
            TagDescription(6L, "sex", R.drawable.icon_sex_ena, R.drawable.icon_sex_dis),
            TagDescription(7L, "talking", R.drawable.icon_talking_ena, R.drawable.icon_talking_dis),
            TagDescription(8L, "travel", R.drawable.icon_travel_ena, R.drawable.icon_travel_dis)
        )

        fun getTagDescription(tag: Tag): TagDescription {
            val tagId = tag.id
            val tagName = tag.name
            val tagDesc = TAGS_MAP.find { tagDescription -> tagDescription.id == tagId }
            if (tagDesc != null)
                if (tagDesc.name == tagName) return tagDesc
                else throw IllegalStateException("Tag id: $tagId has no matching name.")
            else
                throw IllegalStateException("No such tag id: $tagId.")
        }
    }
}