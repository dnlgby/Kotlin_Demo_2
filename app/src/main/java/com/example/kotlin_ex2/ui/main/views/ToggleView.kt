package com.example.kotlin_ex2.ui.main.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import com.example.kotlin_ex2.R

class ToggleView : ImageView {


    private var onDrawable: Drawable? = null
    private var offDrawable: Drawable? = null
    private lateinit var stateMap: Map<Boolean, Drawable?>
    private var toggleState = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadAttributes(attrs)
        loadInitialState()
    }

    private fun loadAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleView)
        onDrawable = typedArray.getDrawable(R.styleable.ToggleView_on_drawable)
        offDrawable = typedArray.getDrawable(R.styleable.ToggleView_off_drawable)
        stateMap = mapOf(false to offDrawable, true to onDrawable)
    }

    private fun loadInitialState() {
        setImageDrawable(stateMap[false])
        setOnClickListener {
            toggleState = !toggleState
            setImageDrawable(stateMap[toggleState])
        }
    }

    fun enabled(): Boolean {
        return toggleState
    }
}