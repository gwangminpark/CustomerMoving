package insung.moving.customer.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import insung.moving.customer.dialog.decorators.DefaultDecorator;
import insung.moving.customer.dialog.decorators.HighlightLunarDecorator;
import insung.moving.customer.dialog.decorators.OneDayDecorator;
import insung.moving.customer.dialog.decorators.SaturdayDecorator;
import insung.moving.customer.dialog.decorators.SundayDecorator;
import insung.moving.customer.R;
import insung.moving.customer.databinding.MovingdayDialogBinding;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatTitleFormatter;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by Administrator on 2017-08-30.
 */

public class MovingDayDialogActivity extends BaseActivity implements OnDateSelectedListener {

    MovingdayDialogBinding binding;

    private static final TitleFormatter DEFAULT_TITLE_FORMATTER = new DateFormatTitleFormatter( new SimpleDateFormat( "yyyy년 MM월" ) );
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    private String shot_Day;
    //날짜 저장하기 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.movingday_dialog );

        binding.calendarView.setOnDateChangedListener( this );
        //  binding.calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        GregorianCalendar today = new GregorianCalendar();

        long now = System.currentTimeMillis();
        Date date = new Date( now );
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        String getTime = sdf.format( date );
        //현재 날짜

        int Year = today.get( Calendar.YEAR );
        int Month = today.get( Calendar.MONTH );
        int Date = today.get( Calendar.DATE );
        int Hour = today.get( Calendar.HOUR_OF_DAY );

        binding.calendarView.setOnDateChangedListener( this );
        binding.calendarView.setShowOtherDates( MaterialCalendarView.SHOW_OUT_OF_RANGE );
        binding.calendarView.setTitleFormatter( DEFAULT_TITLE_FORMATTER );


        binding.calendarView.state().edit()
                .setFirstDayOfWeek( Calendar.SUNDAY )
                .setMinimumDate( CalendarDay.from( Year, Month, 1 ) ) // 달력의 시작
                .setMaximumDate( CalendarDay.from( Year, Month + 2, 31 ) ) // 달력의 끝
                .setCalendarDisplayMode( CalendarMode.MONTHS )
                .commit();
        Log.i("날짜", String.valueOf( Month )+Date );
        if (Month < 9) {
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

                if (Day < 13) {
                    Toast.makeText( MovingDayDialogActivity.this, "현재날짜보다 더큰날짜입력", Toast.LENGTH_SHORT );
                }

                Log.i( "Year test", Year + "" );
                Log.i( "Month test", Month + "" );
                Log.i( "Day test", Day + "" );
                Log.i( "shot_Day test", shot_Day + "" );

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

        binding.btnOk.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now = System.currentTimeMillis();
                Date date = new Date( now );
                try {
                    Date check_date = new SimpleDateFormat( "yyyy-MM-dd" ).parse( shot_Day );
                    long calDate = check_date.getTime() - date.getTime();
                    long calDateDays = calDate / (24 * 60 * 60 * 1000);
                    if (calDateDays < 0) {
                        //오늘날짜 기준 이전날짜를 체크 했을경우
                        showToast( "지난 날짜는 선택 불가합니다" );
                    } else {
                        //오늘날짜 또는 이후 날짜를 체크 했을 경우
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra( "result", shot_Day );
                        setResult( RESULT_OK, resultIntent );
                        finish();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //엑티비티 종료시 shot_Day(선택날짜) 같이 보내줌
            }
        } );


        binding.btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        oneDayDecorator.setDate( date.getDate() );
        widget.invalidateDecorators();
    }
}

