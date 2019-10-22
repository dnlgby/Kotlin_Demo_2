package com.example.kotlin_ex2.common

import android.content.Context
import android.util.TypedValue

object Utils {
    object Screen {
        fun dpToPx(context: Context, dp: Int): Int {
            val metrics = context.resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), metrics)
                .toInt()
        }
    }

}