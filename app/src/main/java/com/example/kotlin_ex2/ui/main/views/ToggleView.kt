package com.example.kotlin_ex2.ui.main.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class ToggleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private fun loadAttributes(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.LocationView)
            mBackgroundDrawable =
                typedArray.getDrawable(R.styleable.LocationView_location_background)
        } else {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

}