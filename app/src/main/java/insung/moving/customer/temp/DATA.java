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



    public static boolean bInitTTS;
    public static boolean bInitPushTTS;

    public static int nPushNumber = 0;
    public static int nPushGroup = 0;
    public static int nPushOrderDetailSeq = 0;
    public static int nPushOrderDetailGroup = 0;
    public static String nPushOrderDetailUCode = "";
    public static String nPushOrderDetailMSG = "";

    public static boolean userAgreement;

    public static final int AREA_COUNT = 6;
    public static final int PUSH_AREA_COUNT = 4;
    //오더 거리
    public static int sRefDistance;
    //상차지저장
    public static String[] ArrStartSido = new String[AREA_COUNT];
    public static int nStartSidoCount;
    //하차지저장
    public static String[] ArrDestSido = new String[AREA_COUNT];
    public static int nDestSidoCount;

    //하차지저장
    public static String[] ArrReservationSido = new String[AREA_COUNT];
    public static int nReservationSidoCount;
    //화면읽기 구분
    public static String sTTSType;
    //독차 , 혼적 구분
    public static int nDogHon;
    public static int nReservDogHon;
    //중량
    public static int nWeight;

    //화물읽기
    public static boolean bIsTTS;
    public static boolean bIsPushTTS;

    //옵션 화면 설정
    public static int nFontSize;
    //칼라
    public static int nStartColor, nDestColor, nMoneyColor, nFeeMoneyColor, nBackGroundColor, nEtcColor;
    public static int aStartColor, aDestColor, aMoneyColor, aFeeMoneyColor, aBackGroundColor, aEtcColor;

    //푸쉬\
    public static String[] ArrSelectPushSido = new String[4];
    public static int nPushSidoCount;
    public static boolean bUsePush;
    public static boolean bPushVolume;

    public static String sSelectStartPushSido;
    public static String sSelectDestPushSido;

    //푸시전용 상/하차지 저장  (기본값은 4)
    public static String[] ArrPushStartSido = new String[4];
    public static String[] ArrPushWeight = new String[4];
    public static String[] ArrPushCarType = new String[4];

    public static int nAutoDisIndex = 0;
    public static int nStartAutoMoney = 20000;
    public static int nEndAutoMoney = 500000;
    public static boolean bRbAuto = false;//false 거리,true 거리+금액
    public static String PushStartSidoTitle = "";
    public static String PushWeightTitle = "";
    public static String PushCarTypeTitle = "";

    public static int nPushStartSidoCount;
    public static String[] ArrPushDestSido = new String[4];
    public static int nPushDestSidoCount;
    public static int PushCarTypeCount;

    // 오더 금액 필터
    public static float moneyFilterFrom = 0.0f;
    public static float moneyFilterTo = 1000.0f;


    public static String group_type = "1";
    public static boolean isGroupNameOrderdetail = false;
    public static boolean bIsBackground = true;
    public static int PushOrderSeq = 0;
    public static int PushGroup = 1;
    // 맵 인증시 사용할 시리얼번호
    public static String sSerialNumber;
    public static String PUSH_ID;
    //GPS 좌표
    public static int nLon;
    public static int nLat;

    public static boolean bKorLogin;
    public static boolean bWooriLogin;

    public static String Username = "";
    public static String Password = "";

//    public static UserInfoItem UserInfo = new UserInfoItem();

//    public static UserInfoItem UserInfo2 = new UserInfoItem();

    //중량 멀티선택 저장
//	public static String [] ArrSelectWeight = new String[4];
    public static String[] ArrSelectWeight = new String[6];

    //차종 멀티선택 저장
    public static String[] ArrSelectCarCode = new String[6];
    public static String[] ArrSelectCarType = new String[6];
//	public static String [] ArrSelectCarCode = new String[4];
//	public static String [] ArrSelectCarType = new String[4];

    public static String[] ArrReservStartSido = new String[AREA_COUNT];
    public static int nReservStartSidoCount;
    //하차지저장
    public static String[] ArrReservDestSido = new String[AREA_COUNT];
    public static int nReservDestSidoCount;
    public static String[] ArrReservSelectWeight = new String[6];
    public static String[] ArrReservSelectCarCode = new String[6];
    public static String[] ArrReservSelectCarType = new String[6];

    public static int nImOKTime = 5;
    public static int nMapType;
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

    public static String sUC = "", sUC2 = "", sCCode = "", sCCode2 = "", sMCode = "", sMCode2 = "";

    //public	static	String DIFFKYEONGKIAREA[] = {"경기북", "경기남"};
    public static String DIFFKYEONGKIGUNGU[] =
            {
                    //"파주시,동두천시,포천시,고양시,김포시,의정부시,양주시,남양주시,가평시,고양시,구리시,양평군,연천군,파주시,하남시",
                    //"부천시,광명시,과천시,안양시,군포시,의왕시,시흥시,안산시,수원시,하남시,광주시,이천시,여주시,용인시,평택시,화성시,안성시,오산시,성남시"
                    "파주시,동두천시,포천시,고양시,김포시,의정부시,양주시,남양주시,가평시,구리시,양평군,연천군,하남시,가평군,고양시덕양구,고양시일산동구,고양시일산서구",
                    "부천시,광명시,과천시,안양시,군포시,의왕시,시흥시,안산시,수원시,광주시,이천시,여주시,용인시,평택시,화성시,안성시,오산시,성남시"
            };
    public static boolean bAutoChk = false;

    public static boolean isNomap() {
        if (nMapType == DEFINE.MAP_NO_MAP) return true;
        else return false;
    }

    public static boolean isTmap() {
        if (nMapType == DEFINE.MAP_TMAP) return true;
        else return false;
    }

    public static boolean isRousen() {
        if (nMapType == DEFINE.MAP_ROUSEN) return true;
        else return false;
    }

    public static boolean isMapzine() {
        if (nMapType == DEFINE.MAP_MAPZIN) return true;
        else return false;
    }

    public static boolean isSGMobile() {
        if (nMapType == DEFINE.MAP_SGMOBILE) return true;
        else return false;
    }

    public static boolean isInaviAir() {
        if (nMapType == DEFINE.MAP_INAVI_AIR) return true;
        else return false;
    }

    public static boolean isKimgisa() {
        if (nMapType == DEFINE.MAP_KIMGISA) return true;
        else return false;
    }

    public static boolean isShopnaviMap() {
        if (nMapType == DEFINE.MAP_SHOPNAVIMAP) return true;
        else return false;
    }

    public static boolean isDaumMap() {
        if (nMapType == DEFINE.MAP_DAUM) return true;
        else return false;
    }

    public static boolean isINaviMx() {
        if (nMapType == DEFINE.MAP_INAVI_MX) return true;
        else return false;
    }
}

