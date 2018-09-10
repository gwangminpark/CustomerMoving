package insung.moving.customer.temp;


public class DEFINE {

    public static String SERVER_IP = "114.108.136.95";
    public static int SERVER_PORT = 9500;

    public static final String SERVER_URL = "http://192.168.1.46:7220";
    final static public String DELIMITER = "\30";
    final static public String ROW_DELIMITER = "\31";

    final static public String INTENT_HEAD = "INSUNG_TEST_";

    public static final String networkIntentValue = "NETWORK_STATE";
    final static public String NETWORK_INTENT_FILTER = "INSUNG_MOVING_BUSINESS_NETWORK";


    public static final int HANDLER_NETWORK_ERROR = 100;
    public static final int HANDLER_NETWORK_OK = 101;
    public static final int HANDLER_NETWORK_LOADING = 102;
    public static final int HANDLER_NETWORK_RESTART = 103;
    public static final int HANDLER_NETWORK_CLOSE = 106;

}
