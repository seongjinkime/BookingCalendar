package com.daejin.view.BookingCalendar.components

import CalendarAdapter
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import com.daejin.view.BookingCalendar.R
import com.daejin.view.BookingCalendar.interfaces.SwipeListener
import kotlinx.android.synthetic.main.book_calendar.view.*
import java.util.*
import kotlin.collections.ArrayList

/*
reference
https://medium.com/meetu-engineering/create-your-custom-calendar-view-10ff41f39bfe
https://github.com/dbrain/android-calendarview/tree/master/src/com/danielbrain/calendarview
 */

class BookingCalendarView : LinearLayout {

    lateinit var header: LinearLayout
    lateinit var gridView: GridView
    lateinit var calendarAdapter: CalendarAdapter
    lateinit var calendar: Calendar

    val swipeListener = object : SwipeListener{
        override fun onRightSwipe() {
            onPrevMonth()
        }

        override fun onLeftSwipe() {
            onNextMonth()
        }
    }


    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.book_calendar, this)

        assignUiElements()
        createCalendar()
        setSwipeGesture()
        //setGridviewBorder()
        updateCalendarAdapter()

    }

    private fun assignUiElements() {
        header = findViewById(R.id.book_calendar_header)
        gridView = findViewById(R.id.book_calendar_grid)
    }

    private fun createCalendar() {
        calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
    }

    private fun setSwipeGesture() {
        var swipeDetector: GestureDetector = GestureDetector(context, SwipeGesture(context, swipeListener))
        gridView.setOnTouchListener(OnTouchListener { view, motionEvent -> swipeDetector.onTouchEvent(motionEvent) })
    }

    private fun setGridviewBorder() {
        gridView.setBackgroundColor(Color.GRAY)
        gridView.horizontalSpacing = 1

    }



    private fun onPrevMonth() {
        moveMonth(-1)
        //updateCalendarAdapter()
        book_calander_switcher.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_from_left))
        book_calander_switcher.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_to_right))

        gridView.visibility = View.VISIBLE
        book_calander_switcher.showPrevious()

    }

    private fun onNextMonth() {
        moveMonth(1)
        //updateCalendarAdapter()
        book_calander_switcher.setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_from_right))
        book_calander_switcher.setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_to_left))
        gridView.visibility = View.VISIBLE
        book_calander_switcher.showNext()

    }

    private fun moveMonth(arrow: Int) {
        if (isEdge(arrow)) {
            calendar.set((calendar.get(Calendar.YEAR)+arrow), if(arrow == 1) Calendar.JANUARY else Calendar.DECEMBER, 1)
        } else {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+arrow)
        }
        //update
    }


    private fun isEdge(arrow: Int): Boolean {
        return if (arrow==1) calendar.get(Calendar.MONTH) == Calendar.DECEMBER else return calendar.get(Calendar.MONTH) == Calendar.JANUARY
    }

    /*
    Display dates correctly in grid
    1. The calendar view is seven days wide, all the months will start somewhere in the first row of the grid.
    2. need to figure out what position the month starts at, then fill all of the previous positions with dates of the previous months.
    3. Then filled the days in the current month.
    4. After filling all of the current month days, fill the rest of it with the next month’s dates.
     */

    private fun updateCalendarAdapter() {
        Toast.makeText(context, String.format("%d년 %d월", calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH))+1), Toast.LENGTH_SHORT).show()
        calendarAdapter = CalendarAdapter(context, createCells(), calendar)
        calendarAdapter.gridView = gridView
        gridView.adapter = calendarAdapter
    }

    private fun createCells(): ArrayList<Date> {
        var cells: ArrayList<Date> = ArrayList()
        var cell: Calendar = Calendar.getInstance()
        cell.time = calendar.time
        val monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1
        cell.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)
        val totalDays = monthBeginningCell + calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val daysCount = if (totalDays > 35) 42 else 35
        while(cells.size < daysCount) {
            cells.add(cell.time)
            cell.add(Calendar.DAY_OF_MONTH, 1)
        }
        return cells
    }

}