package com.example.kotlin_ex2.ui.main.views

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout

class TagFilterView(context: Context, attrs: AttributeSet?) : GridLayout(context, attrs) {

    companion object {
        const val DEFAULT_COLUMN_COUNT = 4
        const val DEFAULT_ROW_COUNT = 3
        const val TOGGLE_ALPHA_VAL_ON = 1.0f
        const val TOGGLE_ALPHA_VAL_OFF = 0.5f
    }

    private var toggleViews = mutableListOf<ToggleView>()

    init {
        columnCount = DEFAULT_COLUMN_COUNT
        rowCount = DEFAULT_ROW_COUNT
    }

    fun addTagToggleItem(itemId: Long, offDrawable: Int, onDrawable: Int) {
        val view = ToggleView(context, itemId, offDrawable, onDrawable)
        toggleViews.add(view)
        addView(view)
    }

    fun getEnabledItemIds(): List<Long> {
        val ret = mutableListOf<Long>()
        toggleViews.forEach { if (it.enabled()) ret.add(it.getItemId()!!) }
        return ret
    }

    fun toggleView(toggle: Boolean) {
        alpha = if (toggle) TOGGLE_ALPHA_VAL_ON else TOGGLE_ALPHA_VAL_OFF
        toggleViews.forEach { it.isEnabled = toggle }
    }

    fun resetView() {
        toggleViews.forEach { it.reset() }
    }

}