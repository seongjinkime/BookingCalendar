package com.daejin.view.BookingCalendar.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.daejin.view.BookingCalendar.R

class BookingCalendarDay : LinearLayout {
    lateinit var view: View

    constructor(context: Context, parent: ViewGroup) : super(context) {
        init(context, parent)
    }

    private fun init(context: Context, parent: ViewGroup) {
        var inflater: LayoutInflater = LayoutInflater.from(context);
        this.view = inflater.inflate(R.layout.book_calendar_day, parent, false)

    }
}