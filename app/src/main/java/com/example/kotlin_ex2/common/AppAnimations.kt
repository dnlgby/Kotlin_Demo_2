package com.example.kotlin_ex2.common

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.animation.addListener

object AppAnimations {

    object Utils {
        fun animateImageView(context: Context, target: ImageView, animatorId: Int) {
            val iconAnimator = AnimatorInflater.loadAnimator(context, animatorId)
            iconAnimator.setTarget(target)
            iconAnimator.start()
        }
    }

    object ScalingAnimations {

        fun windowOpenAnimation(view: View, onEnd: (() -> Unit)? = null) {
            val openAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1.1f),
                PropertyValuesHolder.ofFloat("alpha", 0f, 1f)
            )
            openAnimation.duration = 200

            val correctAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f, 1.0f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f, 1.0f)
            )
            correctAnimation.duration = 100

            val animatorSet = AnimatorSet()
            animatorSet.play(openAnimation).before(correctAnimation)
            animatorSet.addListener(onEnd = { if (onEnd != null) onEnd() })
            animatorSet.start()
        }

        fun windowCloseAnimation(view: View, onEnd: (() -> Unit)? = null) {

            val jumpAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.1f)
            )
            jumpAnimation.duration = 100

            val closeAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f, 0.5f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f, 0.5f),
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
            )
            closeAnimation.duration = 100

            val animatorSet = AnimatorSet()
            animatorSet.play(jumpAnimation).before(closeAnimation)
            animatorSet.addListener(onEnd = { if (onEnd != null) onEnd() })
            animatorSet.start()
        }
    }

}