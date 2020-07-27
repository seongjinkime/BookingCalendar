import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.daejin.view.BookingCalendar.R
import com.daejin.view.BookingCalendar.components.BookingCalendarDay
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter: ArrayAdapter<Date> {
    private lateinit var inflater: LayoutInflater
    private lateinit var days: ArrayList<Date>
    lateinit var gridView: GridView
    private lateinit var current: Calendar




    constructor(context: Context, days: ArrayList<Date>, current: Calendar) : super(context,
        R.layout.book_calendar_day, days) {
        inflater = LayoutInflater.from(context)
        this.days = days
        this.current = current
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //day in position
        var calendar = Calendar.getInstance()
        var date: Date? = getItem(position)
        calendar.time = date
        val day = calendar.get(Calendar.DATE)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        //today
        var today: Date = Date()

        var calendarToday = Calendar.getInstance()
        calendarToday.time = today
        var bookCalendarDay = BookingCalendarDay(context, parent)

        var view: View = if (convertView==null) bookCalendarDay.view else convertView
        var tvDay = view.findViewById<TextView>(R.id.book_calendar_day)
        tvDay.setTypeface(null, Typeface.NORMAL)
        tvDay.setTextColor(Color.BLACK)

        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            tvDay.setTextColor(ResourcesCompat.getColor(context.resources, R.color.colorSunday, null))
        }

        if (month != current.get(Calendar.MONTH) || year != current.get(Calendar.YEAR)) {
            tvDay.setTextColor(ResourcesCompat.getColor(context.resources, R.color.colorBlockDay, null))
            if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                tvDay.setTextColor(ResourcesCompat.getColor(context.resources, R.color.colorBlockSunday, null))
            }
        }



        if (day == calendarToday.get(Calendar.DATE) && month == calendarToday.get(Calendar.MONTH) && year == calendarToday.get(Calendar.YEAR)) {
            tvDay.setTextColor(Color.BLUE)
        }



        tvDay.text = String.format("%02d", calendar.get(Calendar.DATE));
        tvDay.gravity = Gravity.CENTER_HORIZONTAL
        tvDay.setBackgroundColor(Color.parseColor("#FFFFFF"))
        val height = gridView.height / (days.size/7)
        val params: ViewGroup.LayoutParams = (view as LinearLayout).layoutParams
        params.height = height-2
        (view as LinearLayout).layoutParams = params

        //tvDay.height = height-2


        return view

    }

}