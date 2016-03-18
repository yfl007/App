package com.example.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import static android.util.Log.d;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "Nick---MainActivity";
    private LocalReceiver localReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d(TAG, "MainActivity.onCreate.this==" + this.toString());
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
//        localBroadcastManager = LocalBroadcastManager.getInstance(this);
//        localReceiver = new LocalReceiver();
//        intentFilter = new IntentFilter("com.example.broadcastbestpractice.FORCE_OFFLINE_BROADCAST");
//        registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                d(TAG, "MainActivity.onClick"+" Button is clicked");
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE_BROADCAST");
                sendBroadcast(intent);
                break;
            }
            default:
                break;
        }
    }


}
