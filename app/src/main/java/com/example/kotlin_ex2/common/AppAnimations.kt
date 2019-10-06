package com.example.kotlin_ex2.common

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View

object AppAnimations {
    object ScalingAnimations {
        fun scaleDown(view: View, min: Float, max: Float, duration: Long) {
            val scaleDownAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", min, max),
                PropertyValuesHolder.ofFloat("scaleY", min, max),
                PropertyValuesHolder.ofFloat("alpha", min, max)
            )
            scaleDownAnimation.duration = duration
            scaleDownAnimation.start()
        }
    }
}