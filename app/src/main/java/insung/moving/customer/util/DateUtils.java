package insung.moving.customer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016-07-06.
 */
public class DateUtils {
    // 년~초
    public static final String YEAR_TO_SECOND_1 = "yyyyMMddHHmmss";
    public static final String YEAR_TO_SECOND_2 = "yyyyMMdd_HHmmss";
    public static final String YEAR_TO_SECOND_3 = "yyyy년 MM월 dd일 HH시 mm분 ss초";
    public static final String YEAR_TO_SECOND_4 = "yyyy-MM-dd aa h:mm:ss";
    public static final String YEAR_TO_SECOND_5 = "yyyy-MM-dd HH:mm";

    // 년~분
    public static final String YEAR_TO_MINUTE_2 = "yyyy년 MM월 dd일 HH시 mm분";
    public static final String YEAR_TO_MINUTE_3 = "yyyy-MM-dd HH:mm";

    // 년~일
    public static final String YEAR_TO_DAY_1 = "yyyyMMdd";
    public static final String YEAR_TO_DAY_2 = "yyyy-MM-dd";
    public static final String YEAR_TO_DAY_3 = "yyyy.MM.dd";
    public static final String YEAR_TO_DAY_4 = "yyyy년  M월  d일  E요일";

    // 년~시
    public static final String YEAR_TO_HOUR_1 = "yyyyMMddHH";

    // 월~분
    public static final String MONTH_TO_MINUTE_2 = "M월 d일 aa h:mm";

    // 시~분
    public static final String HOUR_TO_MINUTE_1 = "HHmm";
    public static final String HOUR_TO_MINUTE_2 = "HH:mm";
    public static final String HOUR_TO_MINUTE_3 = "aa hh:mm";

    public static final String MONTH_TO_DAY = "MM월 dd일";

    /** 현재 시스템 날짜를 원하는 포멧형식으로 가져오기 **/

    public static String getCurrentDateTime(String format) {
        return new SimpleDateFormat(format).format(new Date(System.currentTimeMillis()));
    }
    public static String getCalendarDateTime(String format, Calendar calendar) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static Date setDateTime(String format, String dateString) {
        Date date = new Date();
        try {

           date = new SimpleDateFormat(format).parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
    public static String getDateTime(String format, Date date) {
        return new SimpleDateFormat(format).format(date.getTime());
    }
}
