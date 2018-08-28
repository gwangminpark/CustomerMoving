package insung.moving.customer.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by user on 2018-08-28.
 */

public class CampaignTrackingReceiver extends BroadcastReceiver {
    private static String referrer = "";
    @Override

    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        if (extras != null) {
            referrer = extras.getString("referrer");
        }
////
        Log.i("TESTAAA", "referrer is : " + referrer);

    }

}
