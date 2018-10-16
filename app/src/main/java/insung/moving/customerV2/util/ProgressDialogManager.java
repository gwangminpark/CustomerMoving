package insung.moving.customerV2.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by kim on 2016-12-31.
 */

public class ProgressDialogManager {
    public static ProgressDialog showSingle(Context context, ProgressDialog progressDialog, String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(context, title, message);
        } else {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);

            if (progressDialog.isShowing() == false) {
                progressDialog.show();
            }
        }

        return progressDialog;
    }

    public static void dismiss(DialogInterface d) {
        if (d == null)
            return;

        try {
            if (d instanceof AlertDialog) {
                if (((AlertDialog) d).isShowing())
                    ((AlertDialog) d).dismiss();

                return;
            }

            if (d instanceof ProgressDialog) {
                if (((ProgressDialog) d).isShowing())
                    ((ProgressDialog) d).dismiss();

                return;
            }

            if (d instanceof Dialog) {
                if (((Dialog) d).isShowing())
                    ((Dialog) d).dismiss();

                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismiss(DialogInterface d1, DialogInterface d2) {
        dismiss(d1);
        dismiss(d2);
    }
}
