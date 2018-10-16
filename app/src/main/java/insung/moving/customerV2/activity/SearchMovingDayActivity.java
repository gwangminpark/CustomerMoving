package insung.moving.customerV2.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;


import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import insung.moving.customerV2.dialog.decorators.HighlightLunarDecorator;
import insung.moving.customerV2.dialog.decorators.OneDayDecorator;
import insung.moving.customerV2.dialog.decorators.SaturdayDecorator;
import insung.moving.customerV2.dialog.decorators.SundayDecorator;
import insung.moving.customerV2.R;
import insung.moving.customerV2.databinding.ActivityMovingSearchBinding;
import insung.moving.customerV2.databinding.NotittleToolbarBinding;
/**
 * Created by user on 2018-07-13.
 */

public class SearchMovingDayActivity extends BaseActivity implements OnDateSelectedListener {

    private static final TitleFormatter DEFAULT_TITLE_FORMATTER = new DateFormatTitleFormatter( new SimpleDateFormat( "yyyy년 MM월" ) );
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private static String shot_Day;
    private ActivityMovingSearchBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_moving_search );
        notittleToolbarBinding = DataBindingUtil.bind( binding.notittleToolbar.getRoot() );
        notittleToolbarBinding.toolbarTitle.setText( "이삿날 찾기" );
        GregorianCalendar today = new GregorianCalendar();
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        int Year = today.get( Calendar.YEAR );
        int Month = today.get( Calendar.MONTH );
        int Date = today.get( Calendar.DATE );

        setSupportActionBar( notittleToolbarBinding.toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        binding.calendarView.setOnDateChangedListener( this );
        binding.calendarView.setShowOtherDates( MaterialCalendarView.SHOW_OUT_OF_RANGE );
        binding.calendarView.setTitleFormatter( DEFAULT_TITLE_FORMATTER );
        binding.calendarView.state().edit()
                .setFirstDayOfWeek( Calendar.SUNDAY )
                .setMinimumDate( CalendarDay.from( Year, Month, 1 ) ) // 달력의 시작
                .setMaximumDate( CalendarDay.from( Year, Month + 10, 31 ) ) // 달력의 끝
                .setCalendarDisplayMode( CalendarMode.MONTHS )
                .commit();

        if (Month < 10) {
            if (Date < 10) {
                shot_Day = Year + "-0" + (Month + 1) + "-0" + Date;
            } else {
                shot_Day = Year + "-0" + (Month + 1) + "-" + Date;
            }
        } else {
            if (Date < 10) {
                shot_Day = Year + "-" + (Month + 1) + "-0" + Date;
            } else {
                shot_Day = Year + "-" + (Month + 1) + "-" + Date;
            }
            // 기본값으로 오늘 날짜 지정
            // 날짜 데이터를 2018-7-9  -> 2018-07-09 형식으로 만들어줌
        }

        binding.calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new HighlightLunarDecorator(),
                oneDayDecorator );
        binding.calendarView.setOnDateChangedListener( new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                oneDayDecorator.setDate( date.getDate() );
                widget.invalidateDecorators();

                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                if (Month < 10) {
                    if (Day < 10) {
                        shot_Day = Year + "-0" + Month + "-0" + Day;
                    } else {
                        shot_Day = Year + "-0" + Month + "-" + Day;
                    }
                } else {
                    if (Day < 10) {
                        shot_Day = Year + "-" + Month + "-0" + Day;
                    } else {
                        shot_Day = Year + "-" + Month + "-" + Day;
                    }
                }
                // 날짜 데이터를 2018-7-9  -> 2018-07-09 형식으로 만들어줌

                binding.calendarView.clearSelection();

            }

        } );
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected( item );
    }
}
