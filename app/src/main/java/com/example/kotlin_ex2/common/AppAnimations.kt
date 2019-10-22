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

    object ToggleAnimation {
        fun disableAlphaAnimation(
            view: View,
            onStart: (() -> Unit)? = null,
            onEnd: (() -> Unit)? = null
        ) {
            val disableAlphaAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("alpha", 1f, 0.4f)
            )
            disableAlphaAnimation.duration = 200
            disableAlphaAnimation.addListener(
                onStart = { if (onStart != null) onStart() },
                onEnd = { if (onEnd != null) onEnd() }
            )
            disableAlphaAnimation.start()
        }

        fun enableAlphaAnimation(
            view: View,
            onStart: (() -> Unit)? = null,
            onEnd: (() -> Unit)? = null
        ) {
            val enableAlphaAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("alpha", 0.4f, 1f)
            )
            enableAlphaAnimation.duration = 200
            enableAlphaAnimation.addListener(
                onStart = { if (onStart != null) onStart() },
                onEnd = { if (onEnd != null) onEnd() }
            )
            enableAlphaAnimation.start()
        }
    }

    object ScalingAnimations {

        fun windowOpenAnimation(
            view: View,
            onStart: (() -> Unit)? = null,
            onEnd: (() -> Unit)? = null
        ) {
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
            animatorSet.addListener(
                onStart = { if (onStart != null) onStart() },
                onEnd = { if (onEnd != null) onEnd() }
            )
            animatorSet.start()
        }

        fun windowCloseAnimation(
            view: View,
            onStart: (() -> Unit)? = null,
            onEnd: (() -> Unit)? = null
        ) {

            val jumpAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.1f)
            )
            jumpAnimation.duration = 100

            val closeAnimation = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 1.1f, 0.8f),
                PropertyValuesHolder.ofFloat("scaleY", 1.1f, 0.8f),
                PropertyValuesHolder.ofFloat("alpha", 1f, 0f)
            )
            closeAnimation.duration = 50

            val animatorSet = AnimatorSet()
            animatorSet.play(jumpAnimation).before(closeAnimation)
            animatorSet.addListener(
                onStart = { if (onStart != null) onStart() },
                onEnd = { if (onEnd != null) onEnd() }
            )
            animatorSet.start()
        }
    }

}