package com.daejin.view.BookingCalendar.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.daejin.view.BookingCalendar.R

class BookingCard : ConstraintLayout {
    constructor(context: Context) : super(context){
        init(context)
    }
    constructor(context: Context, attrs:AttributeSet) : super(context, attrs){
        init(context)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context)
    }

    private fun init(context: Context) {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.book_card, this);

    }

}