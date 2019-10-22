package com.example.kotlin_ex2.common

import com.example.kotlin_ex2.R

object Constants {

    object Main {
        const val WHATSAPP_INVITATION_LINK_PREFIX = "https://chat.whatsapp.com/invite/"
    }

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
            TagDescription(16L, "dating", R.drawable.icon_dating_ena, R.drawable.icon_dating_dis),
            TagDescription(17L, "family", R.drawable.icon_family_ena, R.drawable.icon_family_dis),
            TagDescription(18L, "food", R.drawable.icon_food_ena, R.drawable.icon_food_dis),
            TagDescription(19L, "funny", R.drawable.icon_funny_ena, R.drawable.icon_funny_dis),
            TagDescription(
                20L,
                "parenting",
                R.drawable.icon_parenting_ena,
                R.drawable.icon_parenting_dis
            ),
            TagDescription(21L, "sex", R.drawable.icon_sex_ena, R.drawable.icon_sex_dis),
            TagDescription(
                22L,
                "talking",
                R.drawable.icon_talking_ena,
                R.drawable.icon_talking_dis
            ),
            TagDescription(23L, "travel", R.drawable.icon_travel_ena, R.drawable.icon_travel_dis)
        )

        fun getTagDescription(tagId: Long): TagDescription {
            val tagDesc = TAGS_MAP.find { tagDescription -> tagDescription.id == tagId }
            checkNotNull(tagDesc) { "No such tag id: $tagId." }
            return tagDesc
        }
    }
}