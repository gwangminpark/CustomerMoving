package insung.moving.customer.dialog.decorators;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

/**
 * Created by samsung on 2017-04-12.
 */

public class DefaultDecorator implements DayViewDecorator {

    private final Calendar calendar = Calendar.getInstance();

    public DefaultDecorator() {
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);


            int weekDay = 7;
           // Log.i( "weekDay", String.valueOf( weekDay ) );
            return weekDay == Calendar.SUNDAY;

    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.GREEN));
    }
}
