package insung.moving.customer.temp;


public class PROTOCOL{
	/*test*/
	public static final int INSERT_DORDER_FOR_CUST_C = 20001;
	public static final int GET_DORDER_FOR_CUST = 20002;
	public static final int GET_VERSION_CUST=10010;

	public static final int GET_DAUM_ADDR_PARSING = 99994;
	public static final int GET_DAUM_ADDR  = 99995;
	public static final int GET_MAP_ADDR = 29992;
	public static final int PST_PING  = 29999;
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

	final static public int PE_OK					= 106;

}