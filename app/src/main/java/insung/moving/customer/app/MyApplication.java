package insung.moving.customer.app;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import insung.moving.customer.model.HeightArrayItem;
import insung.moving.customer.model.SidoArrayItem;
import insung.moving.customer.temp.DATA;
import insung.moving.customer.util.LogUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class MyApplication extends Application {
    private static MyApplication mInstance;
    private static boolean IS_DEBUG_MODE;

    private ArrayList<String> sidoItems;    // 시도 리스트
    private ArrayList<String> sidoCodes;    // 시도 코드
    private ArrayList<String> heightItems; //층 리스트


    public SidoArrayItem sidoArrayItem;
    public HeightArrayItem heightArrayItem;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("디스s", String.valueOf(this));
        mInstance = this;
        IS_DEBUG_MODE = isDebuggable(this);

        sidoArrayItem = new SidoArrayItem();
        heightArrayItem= new HeightArrayItem();


        heightItems = new ArrayList<>();

        for (int i = 0; i < DATA.height.length; i++) {
           heightItems.add( DATA.height[i]);
        }

       heightArrayItem.setHeightItems( heightItems );


/////////
        sidoItems = new ArrayList<>();
        sidoCodes = new ArrayList<>();

        for (int i = 0; i < DATA.sidoName.length; i++) {
            sidoItems.add( DATA.sidoName[i]);
            sidoCodes.add( DATA.sidoCode[i]);
        }

        sidoArrayItem.setSidoItems(sidoItems);
        sidoArrayItem.setSidoCodes(sidoCodes);

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
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
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

    public void getRunActivity()	{
        ActivityManager activity_manager = (ActivityManager)getSystemService( Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> task_info = activity_manager.getRunningTasks(9999);

        for(int i=0; i<task_info.size(); i++) {
            LogUtil.d("[" + i + "] activitsy:"+ task_info.get(i).topActivity.getPackageName() + " >> " + task_info.get(i).topActivity.getClassName());
        }
    }
}
