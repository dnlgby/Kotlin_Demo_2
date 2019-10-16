package com.example.kotlin_ex2.ui.main.views

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class TagsView(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    companion object {
        const val DEFAULT_DRAWABLE_SIZE = 25
        const val DEFAULT_MARGIN = 10
        const val DEFAULT_ALPHA = 0.65f
    }

    init {
        orientation = HORIZONTAL
    }

    fun addTagView(drawable: Int, size: Int = DEFAULT_DRAWABLE_SIZE) {
        var drawableSizePix =
            (size * Resources.getSystem().displayMetrics.density).toInt()
        val drawable = ContextCompat.getDrawable(context, drawable)
        val imageView = ImageView(context)

        imageView.apply {
            val params = MarginLayoutParams(drawableSizePix, drawableSizePix)
            params.setMargins(
                DEFAULT_MARGIN,
                DEFAULT_MARGIN,
                DEFAULT_MARGIN,
                DEFAULT_MARGIN
            )
            layoutParams = params
            setImageDrawable(drawable)
            alpha = DEFAULT_ALPHA
        }
        addView(imageView)
    }

    fun clear() {
        removeAllViews()
    }

}
