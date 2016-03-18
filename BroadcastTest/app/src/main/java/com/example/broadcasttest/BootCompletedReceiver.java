package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.util.Log.d;

/**
 * Created by Nick on 2016/3/2.
 */
public class BootCompletedReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompletedReceiver";

    public BootCompletedReceiver() {
        super();
//        Log.d(TAG, "BootCompletedReceiver");
        d(TAG, "BootCompletedReceiver.BootCompletedReceiver.");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Phone completed to boot",Toast.LENGTH_SHORT).show();
    }
}
