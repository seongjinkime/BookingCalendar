package com.daejin.view.BookingCalendar.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView

public class ExpandableGridView: GridView {
    var expanded: Boolean = false

    constructor(context: Context) : super (context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle){}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (expanded) {
            val expandSpec = MeasureSpec.makeMeasureSpec(View.MEASURED_SIZE_MASK, MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, expandSpec)
            var params = layoutParams
            params.height = measuredHeight
            layoutParams = params
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

}