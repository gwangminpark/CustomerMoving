package insung.moving.customer.app;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import insung.moving.customer.model.SidoArrayItem;
import insung.moving.customer.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static boolean IS_DEBUG_MODE;

    private static ArrayList<String> sidoItems;    // 시도 리스트
    private static ArrayList<String> sidoCodes;    // 시도 코드
    public static SidoArrayItem sidoArrayItem;
    public static int nLon;
    public static int nLat;

    public static final String[] sidoName = {
            "서울", "부산", "대구", "인천", "광주", "대전", "울산",
            "세종", "경기", "강원", "충북", "충남", "전북", "전남",
            "경북", "경남", "제주"
    };
    public static final String[] sidoCode = {
            "1100000000", "2600000000", "2700000000", "2800000000",
            "2900000000", "3000000000", "3100000000", "3600000000",
            "4100000000", "4200000000", "4300000000", "4400000000",
            "4500000000", "4600000000", "4700000000", "4800000000", "5000000000"
    };

    public static String SERVER_IP = "114.108.136.95";
    public static int SERVER_PORT = 9500;

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

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        IS_DEBUG_MODE = isDebuggable( this );
        sidoArrayItem = new SidoArrayItem();
        sidoItems = new ArrayList<>();
        sidoCodes = new ArrayList<>();

        for (int i = 0; i < this.sidoName.length; i++) {
            sidoItems.add( this.sidoName[i] );
            sidoCodes.add( this.sidoCode[i] );
        }

        sidoArrayItem.setSidoItems( sidoItems );
        sidoArrayItem.setSidoCodes( sidoCodes );
        //startService(); // 서비스 시작
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    // 디버그모드 유무 체크
    private boolean isDebuggable(Context context) {
        boolean debuggable = false;
        PackageManager pm = context.getPackageManager();

        try {
            ApplicationInfo appinfo = pm.getApplicationInfo( context.getPackageName(), 0 );
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (NameNotFoundException e) {
        /* debuggable variable will remain false */
        }

        return debuggable;
    }


    //
    public static boolean isDebugMode() {
        return IS_DEBUG_MODE;
    }

    public void getRunActivity() {
        ActivityManager activity_manager = (ActivityManager) getSystemService( Context.ACTIVITY_SERVICE );
        List<RunningTaskInfo> task_info = activity_manager.getRunningTasks( 9999 );
        for (int i = 0; i < task_info.size(); i++) {
            LogUtil.d( "[" + i + "] activitsy:" + task_info.get( i ).topActivity.getPackageName() + " >> " + task_info.get( i ).topActivity.getClassName() );
        }
    }
}
