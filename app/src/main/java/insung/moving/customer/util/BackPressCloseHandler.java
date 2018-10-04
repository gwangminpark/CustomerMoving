package insung.moving.customer.util;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import insung.moving.customer.activity.MainActivity;
import insung.moving.customer.activity.SplashActivity;

/**
 * Created by user on 2018-07-20.
 */

public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel();

            Intent t = new Intent(activity, MainActivity.class);
         //   activity.startActivity(t);

            android.os.Process.killProcess(android.os.Process.myPid());
            activity.moveTaskToBack(true);
            activity.finish();
            ((SplashActivity)SplashActivity.context).cancle();
            Log.i("핑쓰레드","2");
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }

}

