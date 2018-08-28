package insung.moving.customer.util;


import insung.moving.customer.app.MyApplication;

public class LogUtil {
    private static String TAG = "LogUtil";

    public static void v(String message) {
        if (MyApplication.isDebugMode()) {
            android.util.Log.v(TAG, buildLogMsg(message));
        }
    }

    public static void d(String message) {
        if (MyApplication.isDebugMode()) {
            android.util.Log.d(TAG, buildLogMsg(message));
        }
    }

    public static void i( String message) {
        if (MyApplication.isDebugMode()) {
            android.util.Log.i(TAG, buildLogMsg(message));
        }
    }

    public static void w(String message) {
        if (MyApplication.isDebugMode()) {
            android.util.Log.w(TAG, buildLogMsg(message));
        }
    }

    public static void e(String message) {
        if (MyApplication.isDebugMode()) {
            android.util.Log.e(TAG, buildLogMsg(message));
        }
    }

    private static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("] ");
        sb.append(message);
        return sb.toString();
    }
}