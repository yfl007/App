package com.example.nick.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/4/11.
 */
public class MyIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = "Nick-MyIntentService";
    public MyIntentService() {
        super("MyIntentService");
        d(TAG, "MyIntentService.MyIntentService.");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        d(TAG, "MyIntentService.onHandleIntent.current thread id = "+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        d(TAG, "MyIntentService.onDestroy.");
    }
}
