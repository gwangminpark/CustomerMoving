package insung.moving.customerV2.util;

import android.app.Activity;
import android.content.Intent;

import insung.moving.customerV2.service.SocketService;


/**
 * Created by Administrator on 2016-06-28.
 */
public class KeyHandleUtil {
    private static final int BACK_KEY_DELAY = 3000;  // 대기시간
    private static long backKeyPressedTime = 0;    // 뒤로가기 누른 시간

    public static void doubleBackFinish(Activity activity) {
        if (System.currentTimeMillis() > (backKeyPressedTime + BACK_KEY_DELAY)) {
            backKeyPressedTime = System.currentTimeMillis();

            return;
        }

        activity.finish();
    }

    public static void doubleBackServiceFinish(Activity activity, SocketService service) {
        if (System.currentTimeMillis() > (backKeyPressedTime + BACK_KEY_DELAY)) {
            backKeyPressedTime = System.currentTimeMillis();
            //Toast.makeText(activity, activity.getString(R.string.msg_confirm_double_back_pressed), Toast.LENGTH_SHORT).show();
            return;
        }
        if(service != null){
            service.StopThread();

            Intent intent = new Intent(activity, SocketService.class);
            activity.stopService(intent);
        }
        activity.finish();
    }


}