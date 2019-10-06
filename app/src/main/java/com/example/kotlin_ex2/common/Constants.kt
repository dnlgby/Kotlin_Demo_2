package com.example.kotlin_ex2.common

import com.example.kotlin_ex2.R

object Constants {
    object ServerConstants {
        const val SERVER_BASE_URL = "http://ec2-3-15-150-102.us-east-2.compute.amazonaws.com/"
    }

    object ContextConstats {
        data class KEY_DESC(val name: String, val drawableOn: Int, val drawableOff: Int)

        val KEYS_MAP = mapOf(
            '1' to KEY_DESC("dating", R.drawable.icon_dating_ena, R.drawable.icon_dating_dis)
        )
    }
}