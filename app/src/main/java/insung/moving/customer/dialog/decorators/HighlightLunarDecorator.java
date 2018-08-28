package insung.moving.customer.dialog.decorators;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.ibm.icu.util.ChineseCalendar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Highlight Saturdays and Sundays with a background
 */
public class HighlightLunarDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();
    private final Drawable highlightDrawable;
    private static final int color = Color.parseColor("#228BC34A");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public HighlightLunarDecorator() {
        highlightDrawable = new ColorDrawable(color);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);

        String lunar = simpleDateFormat.format(calendar.getTime());
        if(LunarCalendar(lunar)){
            return true;
        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(highlightDrawable);

    }


    public boolean LunarCalendar(String solarDay){
        ChineseCalendar chinaCal = new ChineseCalendar();
        Calendar cal = Calendar.getInstance() ;

        cal.set(Calendar.YEAR, Integer.parseInt(solarDay.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(solarDay.substring(5, 7)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(solarDay.substring(8,10)));
        chinaCal.setTimeInMillis(cal.getTimeInMillis());


        int chinaYY = chinaCal.get(ChineseCalendar.EXTENDED_YEAR) - 2637 ;
        int chinaMM = chinaCal.get(ChineseCalendar.MONTH) + 1;
        int chinaDD = chinaCal.get(ChineseCalendar.DAY_OF_MONTH);
        String day = ""+chinaDD;

        String getLunaDay = day.substring(day.length() - 1, day.length());

        if(getLunaDay.equals("0")||getLunaDay.equals("9")){
            // 손없는날 true 반환
            return true;
        }
        return false;
    }
}
