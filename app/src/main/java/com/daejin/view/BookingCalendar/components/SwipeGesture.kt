package com.daejin.view.BookingCalendar.components

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.daejin.view.BookingCalendar.interfaces.SwipeListener

class SwipeGesture: GestureDetector.SimpleOnGestureListener {
    var swipeMinDistance: Int
    var swipeThresholdVelocity: Int
    lateinit var swipeListener: SwipeListener

    constructor(context: Context) {
        val viewConfig = ViewConfiguration.get(context)
        this.swipeMinDistance = viewConfig.scaledTouchSlop
        this.swipeThresholdVelocity = viewConfig.scaledMinimumFlingVelocity
    }

    constructor(context: Context, swipeListener: SwipeListener) {
        val viewConfig = ViewConfiguration.get(context)
        this.swipeMinDistance = viewConfig.scaledTouchSlop
        this.swipeThresholdVelocity = viewConfig.scaledMinimumFlingVelocity
        this.swipeListener = swipeListener
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if(e1.x - e2.x > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
            swipeListener.onLeftSwipe()
        } else if(e2.x - e1.x > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
            swipeListener.onRightSwipe()
        }
        return false
    }

}
