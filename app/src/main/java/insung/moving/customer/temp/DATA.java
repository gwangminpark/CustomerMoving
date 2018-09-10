package insung.moving.customer.temp;


public class DATA {
    //음성출력 초기화 성공여부

    public static final String [] sidoName = {
            "서울","부산","대구","인천","광주","대전","울산",
            "세종","경기","강원","충북","충남","전북","전남",
            "경북","경남","제주"
    };
    public static final String [] sidoCode = {
            "1100000000", "2600000000", "2700000000", "2800000000",
            "2900000000", "3000000000", "3100000000", "3600000000",
            "4100000000", "4200000000", "4300000000", "4400000000",
            "4500000000", "4600000000", "4700000000", "4800000000", "5000000000"
    };

    public static final String [] height = {
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"


    };

    //GPS 좌표
    public static int nLon;
    public static int nLat;


    private static final long REALTIME_ACCOUNT_TIME_LIMIT = 120000;

    public static long GetRealTimeLimit() {
        return REALTIME_ACCOUNT_TIME_LIMIT;
    }

    private static long requestRealtimeAccount = 0;

    public static long GetRealTimeAccountRequestTime() {
        return System.currentTimeMillis() - requestRealtimeAccount;
    }

    public static void SetRealTimeAccountRequestTime() {
        requestRealtimeAccount = System.currentTimeMillis();
    }




}

