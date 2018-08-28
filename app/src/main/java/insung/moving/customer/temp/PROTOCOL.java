package insung.moving.customer.temp;


public class PROTOCOL{
	/*test*/
	public static final int INSERT_DORDER_FOR_CUST_C = 20001;
	public static final int GET_DORDER_FOR_CUST = 20002;
	public static final int GET_VERSION_CUST=10010;

	public static final int GET_DAUM_ADDR_PARSING = 99994;
	public static final int GET_DAUM_ADDR  = 99995;
	public static final int GET_MAP_ADDR = 29992;
	//public static final int GET_MAP_ADDR = 99992;

	/* type */
	final static public int PT_REQUEST					= 101;
	final static public int PT_RESPONSE					= 102;
	final static public int PT_OK								= 103;
	final static public int PT_CANCEL						= 104;
	final static public int PT_ERROR						= 105;
	final static public int PT_DATA							= 106;
	final static public int PT_MESSAGE					= 107;
	
	final static public int HANDLER_MESSAGE_LOGIN_START			= 1;
	final static public int HANDLER_MESSAGE_LOGIN_RESTART		= 2;
	final static public int	HANDLER_MESSAGE_SOCKET_DISCONNECT_FOR_RESTART	= 3;
	final static public int HANDLER_MESSAGE_SOCKET_CONNECT_FOR_RESTART = 4;
	final static public int HANDLER_MESSAGE_ITEM_CLICKED		= 5;
	final static public int HANDLER_MESSAGE_LOADING_SCREEN		= 6;
	final static public int HANDLER_MESSAGE_CLOSE_LOADING_SCREEN = 7;
	final static public int HANDLER_MESSAGE_PICKUP_TIME			 = 8;
	
	
	/* sub type */
	final static public int PST_LOGIN_AND_DB_NEW						= 187; //로그인
	final static public int PST_PING = 29999;
	final static public int PST_LOGIN                      = 101; //로그인
	final static public int PST_GPS_DATA					= 102; //GPS
	
	final static public int HANDLER_MESSAGE_ORDER_SEARCH		= 3000;	//오더 조회 팝업 문구 변경을 위한 메세지
	
	
	
	final static public int PST_ORDER_BAECHA			= 103; //오더배차
	final static public int PST_SYSTEM_MESSAGE		= 104;
	//final static public int PST_ORDER_PICKUP			= 104; //오더픽업
	final static public int PST_ORDER_COMP				= 105; //오더완료
	final static public int PST_ORDER_DETAIL			= 106; //오더상세조회
	final static public int PST_ORDER_COMP_SUCH	= 107; //오더완료
	final static public int PST_CAR_ORDER_SUCH		= 108; //기사콜조회
	final static public int PST_CAR_ORDER_DETAIL	= 109; //기사콜상세조회
	final static public int PST_ORDER_COMPLETE		= 110;
//	final static public int PST_CAR_ORDER_UPDATE	= 111; //기사콜수정
	final static public int PST_BOARD_SUCH				= 112; //게시판조회
	final static public int PST_BOARD_DETAIL			= 113; //게시판상세조회
	
	// 퀵화물작업
	final static public int PST_BULLETIN           = 113;
	final static public int PST_BULLETIN_DETAIL     = 114;
	final static public int PST_BULLETIN_GROUP      = 115;
	final static public int PST_DONGSECTOR_SIDO    = 191; // 동지역 (시도이름)
	final static public int PST_DONGSECTOR_GUNGU   = 192; // 동지역 (군구이름)
	final static public int PST_DONGSECTOR_DONG    = 193; // 동지역 (동이름)
	final static public int PST_BULLETIN_CONFIRM        = 195;
	final static public int PST_RIDER_INFO         = 178;
	
	final static public int PST_RIDER_SMSCONF_INSERT = 224;    //SMS인증
	final static public int PST_UPDATE_SUP_INFO    = 229;  
	final static public int PST_MSG_LIST           = 222;  //메시지리스트
    final static public int PST_MSG_DETAIL          = 223;  //메시지상세
	
	   final static public int HANDLER_MESSAGE_COMPLETE_SEARCH     = 4000;
	   final static public int HANDLER_MESSAGE_BOARD_SEARCH        = 5000;
	   final static public int HANDLER_MESSAGE_GROUPBOARD_SEARCH   = 7000;
	//
	final static public int PST_GUNGU_SUCH				= 116; //군구리스트
	final static public int PST_DONG_SUCH				= 117; //동리스트
	final static public int PST_CAR_MSG_SUCH			= 118; //기사메세지리스트
	final static public int PST_CAR_MSG_DETAIL		= 119; //기사메세지상세
	final static public int PST_RIDER_INFORMATION	= 120; //기사정보
	final static public int PST_CAR_AMT_SUCH			= 121; //기사 정산 내역
	final static public int PST_ORDER_PACK_SUCH		= 122; //오더 묶음 조회
	final static public int PST_ORDER_CANCEL			= 123; //오더 배차 취소
//	final static public int PST_CAR_BANK_INFO			= 124; //계좌정보조회
	final static public int PST_ALLOC_CANCEL		= 124;
//	final static public int PST_CAR_TRANS_AMT		= 125; //계좌출금
	final static public int PST_MESSAGE				= 125;
	
	final static public int PST_AMT_LOG_SUCH			= 126; //계좌출금내역조회
	
//	final static public int PST_CAR_ORDER_CANCEL	= 128; //기사콜취소
//	final static public int PST_ORDER_COMP_SUCH2	= 129; //오더완료조회2(컬럼갯수틀림)
	final static public int	PST_CONNECTION_CLOSE	= 129; //접속이 종료됩니다
	final static public int PST_GET_TAX_LIST			= 130; //세금발행목록조회
	final static public int PST_UPDATE_TAX_GBN		= 131; //세금발행요청
	final static public int PST_SIDO_LIST				= 132; //시도목록조회
	
	//final static public int PST_UNHANG_CANCEL					= 135; //운행중 오더 취소
	final static public int PST_D_CALCULATION2		= 135;
	final static public int PST_REGISTRATION					= 136; //회원가입
	final static public int PST_CAR_TYPE_GROUP					= 137; //회원가입 용 차종
//	final static public int PST_CAR_WEIGHT_LIST_GROUP			= 138; //회원가입 용 중량
	final static public int PST_D_ORDER_DETAIL2 	= 138;
//	final static public int PST_BAECHA_RIDER_INFORMATION  = 139; //기사 상세정보 트럭기사
	final static public int PST_ALLOC_REQUEST2     = 139;
	final static public int PST_BAECHA_RIDER_INFORMATION_K  = 140; //기사 상세정보 퀵 1그룹기사
	final static public int PST_BAECHA_RIDER_INFORMATION_Q  = 141; //기사 상세정보 퀵 2그룹기사
	final static public int PST_BOARD_SUCH_NEW = 142; //PDA 게시판 필독유무 조회
	final static public int PST_BOARD_DETAIL_NEW   = 143; //필독 게시글 read 유무 확인
	final static public int PST_UPDATE_BOARD_CONFIRM  = 144; //게시판 글 체크	
	
	final static public int PST_CAR_ORDER_SUCH_NEW = 145; // 오더 목록
	final static public int PST_ORDER_CONFIRM3		= 146;
//	final static public int PST_PUSH_MESSAGE = 146; // 푸쉬메시지
	final static public int PST_TAX_CAR_INFO = 147; // 전자계산서 차량사업자정보 조회
	final static public int PST_TAX_CAR_INFO_UPDATE = 148; // 전자계산서 차량사업자정보 수정
	final static public int PST_CALLCENTER_INFO = 149; // 콜센터정보조회 
	final static public int PST_D_ORDER_DETAIL3		= 150;
//	final static public int PST_ORDER_COPY  					= 158; //오더복사
//	final static public int PST_SET_ORDER_STSTUS		= 159;//오더 상태 변경 
	final static public int PST_CALLCENTER_INFO_NEW = 160; // 콜센터정보조회 
	
	final static public int PST_BAECHA_RIDER_INFORMATION_NEW =161; // 본인이 올린오더에서 배차받은 기사정보 보기
	final static public int PST_CAR_POOL_SIGN =162; // 싸인추가 
    final static public int PST_INSERT_SIGN_CARCODE = 163 ;// 싸인추가전 카코드 보내기 
	final static public int PST_INSERT_CAR_POOL_JK		= 167; //전국물류용 회원가입
	final static public int PST_BANK_LIST  				= 168; //은행리스트
	
	final static public int PST_DORDER_DETAIL_NON_NEW = 169; // 오더상세조회 차정보
	final static public int PST_DORDER_DETAIL_NON = 170; // 오더상세조회 차정보
	final static public int PST_ORDER_BAECHA_AND_PICKUP = 171; // 배차및픽업	
	final static public int PST_BOARD_GROUP_LIST = 172; // 라인별 게시판조회
	final static public int PST_ONLINE_MEMBER  			= 173; // 비로그인 상태에서 그룹정보체크
	final static public int PST_ORDER_SUCH2 = 175; // 라인별 게시판조회
	final static public int PST_ORDER_SIDO_CNT	= 176; // 시도별 오더 카운트
	final static public int PST_INSERT_ORDER_SM   = 177; // 단말기오더등록 스마트물류용(오더대수 와 상차일시 추가)
	final static public int PST_UPDATE_ORDER_SM    =178; // 단말기오더등록 스마트물류용(오더대수 와 상차일시 추가)
	final static public int PST_CAR_AMT_SUCH_NEW  = 179; // 적릭금내역 추가 
	final static public int PST_LOGIN_SIMPLE_ACCEPT  = 181; // 주선회원 로그인
	final static public int PST_CAR_ORDER_SUCH_TOTAL   = 183; // 기사콜조회_NEW
	final static public int PST_SMS_RULE_SEND_TELNO = 184; // 발신번호등록요청
	final static public int PST_GET_AREA_LON_LAT = 185; // LON LAT값
	final static public int PST_INSERT_CAR_POOL_TOTAL = 186; // 싸인코드
//	final static public int PST_GET_AREA_LON_LAT = 185; // LON LAT값
//	final static public int PST_CAR_ORDER_DETAIL2 = 189; // 기사콜상세정보 수정버전
	
	final static public int PST_AUTO_ALLOC_REQUEST	= 188;		//자동배차
	final static public int PST_ALLOC_REQUEST4		= 189;//원터치시 배차
	final static public int PST_UPDATE_SIGN			= 190;
	final static public int PST_AGREEMENT			= 196;		//출근 동의 확인
	final static public int PST_D_ORDER3			= 198;	
	final static public int PST_SHIELD				= 199;
	final static public int PST_CAR_TYPE			= 202; //차종얻어오기
	final static public int PST_CAR_WEIGHT			= 203; //차종얻어오기
	final static public int PST_D_ORDER4			= 204;
	final static public int PST_SET_USER_AMT		= 205;
	final static public int PST_UPDATE_RIDER_PUSH_INFO		= 206;
	final static public int PST_ALLOC_REQUEST5		= 207;
	
	final static public int PST_MESSAGE_OK			= 210;
	final static public int PST_INOUT_REPORT		= 213;
	final static public int PST_RIDER_DAYWORK		= 214;
	final static public int PST_GET_RIDER_ACC		= 215;
	final static public int PST_INSERT_RIDER_TRANSFER_ACCOUNT	= 216;
	final static public int PST_INSERT_RIDER_TRANSFER_ACCOUNT_CANCEL = 217;
	final static public int PST_INSERT_RIDER_REALTIME_TRANSFER_ACCOUNT	= 218;
	final static public int PST_PROVISION          = 219;// 이용약관등 동의 3개월마다
	final static public int PST_RIDER_TRUCK_DAYWORK	= 221;
	final static public int PST_SUP_INFO           = 228;
	final static public int PST_TRUCK_COMPLETED_LIST = 225;
	final static public int PST_COMP_INFO				= 226; //거래처사업자정보
	
	final static public int PST_CAR_ORDER_INSERT  = 230; //인성콜화물 오더등록
    final static public int PST_CAR_ORDER_UPDATE  =  231;//인성콜화물 오더수정
    final static public int PST_GET_AREA_LIST     =  232; //인성콜화물,인성연합화물 시도조회
    final static public int PST_CAR_ORDER_CANCEL  =  233;//인성콜화물 오더취소
    final static public int PST_SET_ORDER_STSTUS  =  234;//인성콜화물 오더상태변경
    
	final static public int PST_CAR_ORDER_SUCH2       = 237; //내가올린오더정보
	final static public int PST_CAR_ORDER_DETAIL2       = 238; //오더 상세 조회
	final static public int PST_ORDER_COPY                     = 239; //오더복사
	final static public int PST_GET_BANKCODE_NAME_CHECK = 242;
	final static public int PST_INSERT_BANKCODE_NAME_LIST = 243;
	final static public int PST_GET_BANKCODE_NAME_URL_CHK = 244;
	final static public int PKG_GET_TRUCK_RESV_ORD		  =	245;
	final static public int PST_ALLOC_REQUEST6			= 246;
	final static public int PST_RIDER_INSERT_SHARE         = 247;
	final static public int PST_INSERT_STANDBY_CAR_LIST  =   248; //대기차량등록 1톤화물만
	final static public int PKG_GET_STANDBY_CAR  =   249; //
	final static public int PST_UPDATE_STANDBY_CAR_LIST  =   250; //
	final static public int PST_UPDATE_RIDER_PUSH_MESSAGE_INFO = 251;

	
	final static public int PST_SELECT_CARTRACE  =   253;
	final static public int PST_DELETE_STANDBY_CAR_LIST  =   254;
	final static public int PST_GET_STANDBY_STATE = 255;
	final static public int PST_GET_STANDBY_GBN = 256; //대기차량상태보고확인
	final static public int PST_UPDATE_STANDBY_GBN = 257; //대기차량상태보고등록
	final static public int PST_UPDATE_STANDBY_TIME = 258; //대기차량시간등록
	final static public int PST_SAVE_PUSH_ORDER = 259; // 운행시 푸쉬오더등록
	final static public int PST_D_ORDER6 = 260;
	final static public int PST_D_ORDER7 = 261;
	final static public int PST_D_ORDER8 = 262;
	final static public int PST_D_ORDER9 = 263;
	final static public int PST_GET_ORDERALLOCCANCEL = 265;
	final static public int PST_SELECT_RIDER_CLAUSE = 264;


	//	final static public int PST_CAR_ORDER_INSERT	= 301;
	final static public int PST_PUSH_MESSAGE		= 302;
	final static public int PST_CAR_WEIGHT_LIST_GROUP = 303;
	final static public int PST_CAR_BANK_INFO = 304;
//	final static public int PST_CAR_ORDER_DETAIL2 = 305;
	
	final static public int PST_RUN_CANCEL			= 212;
	final static public int PST_D_ORDER_DETAIL_TRUCK = 227;//트럭오더상세보기
//	final static public int PST_GET_AREA_LIST     =  232; //인성콜화물,인성연합화물 시도조회
//	final static public int PST_GET_AREA_LIST       = 979; // 상하차지주소검색 (관심지역포함)                    
	
	final static public int PST_MESSAGE_SYSTEM       = 980; // 오더 취소 팝업(전화번호 추가)
	final static public int PST_PDA_MESSAGE          = 981; // 게시판 필독 팝업
	final static public int PST_PDA_MESSAGE_READ     = 982; // 게시판 필독 팝업 확인
	
	final static public int PST_RIDER_GPS_DATA		= 991; //사인파일 저장시작
	
	final static public int PST_SIGN_SAVE_START		= 400;
	final static public int PST_SIGN_SAVE			= 401;
	final static public int PST_SIGN_SAVE_END		= 402;
	
	
	final static public int PST_CAR_TRANS_AMT					= 996; //계좌출금
	final static public int PST_MESSAGE_READ			= 997; //메세지 읽기확인
	
	final static public int PST_SMARTTRS_AUTH		= 301; //화물읽기 옵션유무 
	
	
	/* error code */
	/* error code */
	final static public int PE_INVALID_CID			= 101;
	final static public int PE_ORDER_NOT_FOUND		= 102;
	final static public int PE_ALREADY_ALLOCATED	= 103;
	final static public int PE_INVALID_ORDER		= 104;
	final static public int PE_DEST_LIST_NOT_FOUND	= 105;
	final static public int PE_OK					= 106;
	final static public int PE_ERROR				= 107;
	final static public int PE_NO_DATA				= 108;
	final static public int PE_TIMEOUT_ERROR		= 109;
	final static public int PE_ORDER_ONLY_MAN		= 110;
	final static public int PE_NO_CHARGE			= 111;
	final static public int PE_NOT_COMPLETE_ORDER	= 112;
	final static public int PE_NO_LIMITS			= 113;
	final static public int PE_ATTENDANCE			= 114;
	final static public int PE_QUERY_ERROR			= 115;
	final static public int PE_DUPLICATE_DATE		= 116;
	final static public int PE_LOGIN_OK				= 117;
	final static public int PE_OK_HELP_ME			= 118;
	final static public int PE_ALREADY_HELP_ME		= 119;
	final static public int PE_LOCATION_CHANGED		= 120;
	final static public int PE_INVALID_PROTOCOL		= 121;
	final static public int PE_UNDEFINED_PROTOCOL	= 122;
	final static public int PE_CONNECTION_CLOSE		= 123;
	final static public int PE_CLOSE				= 124;
	final static public int PE_NO_ATTENDANCE		= 125;
	final static public int PE_OK_CONNECTION		= 126;
	final static public int PE_ALREADY_CONNECTION 	= 127;
	final static public int PE_MY_LOAD				= 128;
	final static public int PE_POSITION_SAVE		= 129;
	final static public int PE_NO_POSITION			= 130;
	final static public int PE_NO_RIDER				= 131;
	final static public int PE_NO_CUSTOMER			= 132;
	final static public int PE_FAIL_ALLOCATE		= 133;
	final static public int PE_ATTENDANCE_TIME		= 134;
	final static public int PE_PUNCHOUT_TIME		= 135;
	final static public int PE_YOU_NEED_UPDATE		= 137;	// 사용자 업데이트 필요
	final static public int PE_INVALID_VERSION		= 138;
	final static public int PE_YOU_ARE_BLUELINE_USER = 139;	// 코리아네트워크 다운로드 링크
	final static public int PE_CONNECTION_CLOSE_GGGI = 140;
}