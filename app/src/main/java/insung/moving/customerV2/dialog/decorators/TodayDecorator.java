package insung.moving.customerV2.dialog.decorators;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.Date;

import insung.moving.customerV2.R;


/**
 * Created by Administrator on 2018-07-23.
 */

public class TodayDecorator implements DayViewDecorator {

    private CalendarDay date;
    private final Drawable backgroundDrawable;

    public TodayDecorator(Context context,CalendarDay date) {
        this.date = date;

        backgroundDrawable = context.getResources().getDrawable( R.drawable.today_circle_background);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(backgroundDrawable);
//        view.addSpan(new RelativeSizeSpan(1.4f));
view.addSpan(new DotSpan(5, Color.RED));
    }
    public void setDate(Date date) {
        this.date = CalendarDay.from(date);
    }
}