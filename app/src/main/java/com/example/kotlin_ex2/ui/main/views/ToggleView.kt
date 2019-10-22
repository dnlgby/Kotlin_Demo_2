package com.example.kotlin_ex2.ui.main.views

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.kotlin_ex2.R


class ToggleView : ImageView {

    companion object {
        const val DEFAULT_DRAWABLE_SIZE = 45
        const val DEFAULT_MARGIN = 18
        const val DEFAULT_VALUE_XML_ID = 0
    }

    private var itemId: Long? = null
    private var onDrawable: Drawable? = null
    private var offDrawable: Drawable? = null
    private var drawableSize = DEFAULT_DRAWABLE_SIZE
    private var toggleState = false
    private var onToggledListener: (() -> Unit)? = null

    private lateinit var stateMap: Map<Boolean, Drawable?>


    constructor(
        context: Context, itemId: Long, offDrawable: Int, onDrawable: Int,
        size: Int = DEFAULT_DRAWABLE_SIZE
    )
            : super(context, null) {
        loadAttributes(context, itemId, offDrawable, onDrawable, size)
        loadInitialState()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadXmlAttributes(attrs)
        loadInitialState()
    }

    private fun loadAttributes(
        context: Context,
        itemId: Long,
        offDrawable: Int,
        onDrawable: Int,
        size: Int
    ) {
        this.itemId = itemId
        this.offDrawable = ContextCompat.getDrawable(context, offDrawable)
        this.onDrawable = ContextCompat.getDrawable(context, onDrawable)
        this.drawableSize = size
    }

    private fun loadXmlAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToggleView)
        itemId =
            typedArray.getInteger(R.styleable.ToggleView_item_id, DEFAULT_VALUE_XML_ID).toLong()
        onDrawable = typedArray.getDrawable(R.styleable.ToggleView_on_drawable)
        offDrawable = typedArray.getDrawable(R.styleable.ToggleView_off_drawable)
        drawableSize =
            typedArray.getInteger(R.styleable.ToggleView_drawable_size, DEFAULT_DRAWABLE_SIZE)
    }

    private fun loadInitialState() {
        stateMap = mapOf(false to this.offDrawable, true to this.onDrawable)
        setImageDrawable(stateMap[false])
        setOnClickListener {
            toggleState = !toggleState
            setImageDrawable(stateMap[toggleState])
            onToggledListener?.invoke()
        }
        val sizeInPix = (drawableSize * Resources.getSystem().displayMetrics.density).toInt()
        val params = ViewGroup.MarginLayoutParams(sizeInPix, sizeInPix)
        params.setMargins(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN)
        layoutParams = params
    }

    fun setOnToggledListener(onClickListener: () -> Unit) {
        this.onToggledListener = onClickListener
    }

    fun getItemId(): Long? {
        return itemId
    }

    fun enabled(): Boolean {
        return toggleState
    }

    fun reset() {
        toggleState = false
        setImageDrawable(stateMap[toggleState])
    }
}