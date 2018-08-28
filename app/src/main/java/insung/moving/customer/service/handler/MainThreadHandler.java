package insung.moving.customer.service.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018-07-06.
 */

public class MainThreadHandler<T extends MessageHandlerInterface> extends Handler {

    private final WeakReference<T> mInstance;

    public MainThreadHandler(T activity) {
        // Remove the following line to use the current thread.
        super(Looper.getMainLooper());
        mInstance = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        T activity = mInstance.get();
        if (activity != null) {
            activity.handleMessage(msg);
        }
    }
}