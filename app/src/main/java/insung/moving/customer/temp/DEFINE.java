package insung.moving.customer.temp;


public class DEFINE {
/*   static public String SERVER_IP				= "192.168.1.74";
	static public int	 SERVER_PORT			= 7401;*/
//
/*	static public String SERVER_IP				= "192.168.1.96";
	static public int	 SERVER_PORT			= 2030;*/
/*public static String SERVER_IP = "192.168.1.46";
	public static int SERVER_PORT = 7220;*/
public static String SERVER_IP="114.108.136.95";
public static int SERVER_PORT=9500;

	public static final String SERVER_URL = "http://192.168.1.46:7220";
	final static public String DELIMITER 		= "\30";
	final static public String ROW_DELIMITER	= "\31";

	final static public String VERSION         = "100";

	static public String GALEXYVERSION		= "3.17";
	static public String GALEXYVERSION2		= "3.17";
												//버전업전에 지사 버전도 다 올려야함 
//	final static public String CENTER_CALL 			= "15660024"; //콜센터 전화걸기 무조건 메인으로 해달라는 요청으로 메인으로함..	
//	final static public String CENTER_CALL_KGI 		= "15880772"; //콜센터 전화걸기 무조건 메인으로 해달라는 요청으로 메인으로함..
	
	final static public String GROUP_ID				= "TOTALCALL";
//	final static public String GROUP_ID				= "INSUNG";
	final static public String PDA_TYPE				= "ANDROID_TRUCK";
	final static public String INTENT_HEAD			= "INSUNG_TEST_";

	public static final String networkIntentValue  = "NETWORK_STATE";
	final static public String NETWORK_INTENT_FILTER = "INSUNG_MOVING_BUSINESS_NETWORK";
	//final static public String INTENT_HEAD			= "INSUNG_TRUCKCOOPERATION_";
//	final static public String INTENT_HEAD			= BuildConfig.INTENT_HEAD;
//	final static public String UPDATE_URL			= "http://114.414.co.kr/android/integrationcall.apk";
	//final static public String UPDATE_MARKET_URL	= "http://market.android.com/details?id=insung.";
	//구글용
//	final static public String UPDATE_MARKET_URL	= "market://details?id="+BuildConfig.APPLICATION_ID;
	
	//화물읽기설정
	final static public String READ_ORDERLIST = "read_orderlist";
	
	//약관동의체크
	final static public String CHECK_AGEERMENT = "check_agreement";
	
	//T스토어용 URL PID 
	//final static public String UPDATE_MARKET_URL= "PRODUCT_VIEW/0000674861/0";
	
	//KT용 
	//final static public String UPDATE_MARKET_URL= "8101BF91";
	
	//LG용
	//final static public String UPDATE_MARKET_URL= "Q04011317609";

	public static final int HANDLER_NETWORK_ERROR				= 100;
	public static final int HANDLER_NETWORK_OK					= 101;
	public static final int HANDLER_NETWORK_LOADING				= 102;
	public static final int HANDLER_NETWORK_RESTART				= 103;
	public static final int HANDLER_NETWORK_DATA_RECV_SUCCESS	= 104;
	public static final int HANDLER_NETWORK_DATA_RECV_ERROR		= 105;
	public static final int HANDLER_NETWORK_CLOSE	= 106;

	public static final int HANDLER_ORDER_LIST_AUTO_REFRESH		= 1000;
	public static final int HANDLER_ORDER_LIST_SWIPE_REFRESH	= 1001;
	final static public String SYSTEM_NOTICE 		=	"http://support.insungdata.com/pda_system.html";
	final static public String NOMAL_NOTICE 		 = 	"http://support.insungdata.com/pda_popup.html";
//	final static public String PAKEAGE_NAME  		= BuildConfig.APPLICATION_ID;
	final static public int DLG_REGION 				= 10000;
	final static public int DLG_MENU 				= 10001;
	final static public int DLG_PHOTO 				= 10002;
	final static public int DLG_BOARD 				= 10003;
	final static public int DLG_ORDER_DETAIL		= 10004;
	final static public int DLG_LOCATION			= 10005;
	final static public int DLG_CAR_TYPE			= 10006;
	final static public int DLG_CAR_WEIGHT			= 10007;
	final static public int DLG_ORDER_INSERT		= 10008;
	final static public int DLG_GPS_SETUP			= 10009;
	final static public int DLG_DAY_REPORT			= 10010;	
	final static public int DLG_MGM_TYPE_GB  		= 10011;
	final static public int DLG_BOARDDETAIL     	= 10012;
	final static public int DLG_MENU_BOARD 			= 10013;
	final static public int DLG_VOICE   			= 10014;
	final static public int DLG_MEMBER_REGISTRATION	= 10015;
	final static public int DLG_MEMBER_DETAIL		= 10016;
	final static public int DLG_AGREEMENT			= 10017;
	final static public int DLG_VOICE_SPEAK			= 10018;
	final static public int DLG_SMART_TRS			= 10019;
	final static public int DLG_SYSTEM_NOTICE		= 10020;
	final static public int DLG_NOMAL_NOTICE		= 10021;
	final static public int DLG_REGISTRATION		= 10022;
	final static public int DLG_LOGIN_PAGE			= 10023;
	final static public int DLG_PACKAGE_DELETE		= 10024;
	final static public int DLG_PUSHOPTION			= 10025;
	final static public int DLG_ORDER_REG           = 10026;
	final static public int DLG_MAINTAB           = 10027;
	final static public int DLG_CAR_ORDER_SELECT_START     = 10028;
    final static public int DLG_CAR_ORDER_SELECT_DEST      = 10029;
    final static public int DLG_CAR_ORDER_SELECT2_START    = 10030;
    final static public int DLG_CAR_ORDER_SELECT2_DEST     = 10031;
    final static public int DLG_CAR_ORDER_CARWEIGHT        = 10031;
    final static public int DLG_CAR_ORDER_CARTYPE          = 10032;
    final static public int DLG_CAR_ORDER_LOAD_START       = 10033;
    final static public int DLG_CAR_ORDER_LOAD_DEST        = 10034;
    final static public int DLG_CAR_ORDER_TITLE_START       = 10035;
    final static public int DLG_CAR_ORDER_TITLE_DEST        = 10036;
    final static public int DLG_ORDER_CANCEL_CENTERCALL     = 10037;
    final static public int DLG_ORDER_CANCEL_SYSTEM     = 10038;
    final static public int DLG_CAR_ORDER_DATE_FROM       = 10039;
    final static public int DLG_CAR_ORDER_DATE_TO       = 10040;
    final static public int DLG_CAR_ORDER_STARTDAY        = 10041;
    final static public int DLG_CAR_ORDER_DESTDAY        = 10042;
    
    final static public int HANDLER_MESSAGE_INPROGRESS       = 10043;
    final static public int HANDLER_MESSAGE_ORDER_COUNT_SEARCH       = 10044;
    final static public int HANDLER_MESSAGE_LOGIN_START       = 10045;
    final static public int DLG_AUTOBAECHA        = 10046;
    final static public int DLG_YESNO        = 10047;
    final static public int DLG_NOTIFICATION        = 10048;
    final static public int HANDLER_NOTIFY_CANCEL        = 10049;
    final static public int DLG_WAITINGCAR        = 10050;
    final static public int DLG_ORDERALLOC_DETAIL	= 10051;
	final static public int DLG_AGREEMENTDLG	= 10052;

	final static public int SOUND_ORDER 		= 0;
	final static public int SOUND_BAECHA 		= 1;
	final static public int SOUND_MESSAGE 		= 2;
	final static public int SOUND_ALLOC 		= 3;
	
	final static public int LIST_NEW			= 1;
	final static public int LIST_COMPLETE		= 2;
	final static public int LIST_BOARD			= 3;
	final static public int LIST_GROUP_BOARD	= 4;
	final static public int LIST_TRUCK			= 5;
	
	final static public int DIALOG_PROGRESS_SHOW_LOGIN		= 0;
	final static public int DIALOG_PROGRESS_SHOW_COMPLETE	= 1;
	final static public int DIALOG_PROGRESS_SHOW_BOARD		= 2;
	
	final static public int DIALOG_SHOW_DAY_DIALOG	= 1;
	final static public String DELETE_114 = "insung.integration_114";
	final static public String DELETE_25si = "insung.integration_25si";
	final static public String DELETE_baecha = "insung.integration_baecha";
	final static public String DELETE_bokhab = "insung.integration_bokhab";
	final static public String DELETE_changmyeong = "insung.integration_changmyeong";
	final static public String DELETE_daegil = "insung.integration_daegil";
	final static public String DELETE_daeyang = "insung.integration_daeyang";
	final static public String DELETE_dalguji = "insung.integration_dalguji";
	final static public String DELETE_gana = "insung.integration_gana";
	final static public String DELETE_gogo = "insung.integration_gogo";
	final static public String DELETE_gyeongchang = "insung.integration_gyeongchang";
	final static public String DELETE_hyundai = "insung.integration_hyundai";
	final static public String DELETE_is = "insung.integration_is";
	final static public String DELETE_jk = "insung.integration_jk";
	final static public String DELETE_king = "insung.integration_king";
	final static public String DELETE_korea = "insung.integration_korea";
	final static public String DELETE_kukus = "insung.integration_kukus";
	final static public String DELETE_network = "insung.integration_network";
	final static public String DELETE_smart = "insung.integration_smart";
	final static public String DELETE_snm = "insung.integration_snm";
	final static public String DELETE_speed = "insung.integration_speed";
	final static public String DELETE_sunggwang = "insung.integration_sunggwang";

	final static public	int	HANDLER_MESSAGE_SOUND_ORDER		= 3009;
	final static public	int	HANDLER_MESSAGE_SOUND_BAECHA	= 3010;
	
	final static public int MAP_NO_MAP			= 0;
	final static public int MAP_DAUM			= 1;	
	final static public int MAP_SGMOBILE		= 2;
	final static public int MAP_INAVI_AIR		= 3;
	final static public int MAP_ROUSEN			= 4;
	final static public int MAP_INAVI_MX		= 5;
	final static public int MAP_TMAP			= 6;	
	final static public int MAP_KIMGISA			= 7;
	final static public int MAP_SHOPNAVIMAP		= 8;
	final static public int MAP_MAPZIN			= 9;
//	final static public String DELETE_total = "insung.";
}
