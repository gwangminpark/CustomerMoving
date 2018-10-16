package insung.moving.customerV2.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by user on 2018-08-28.
 */

public class ReferrerReceiver extends BroadcastReceiver {
    public static String referrer = "";
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            referrer = extras.getString("referrer");
        }
///
    }

}
