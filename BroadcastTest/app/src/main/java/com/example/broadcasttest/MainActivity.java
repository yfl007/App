package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.net.NetworkInterface;
import java.nio.BufferUnderflowException;

import static android.util.Log.d;


public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "Nick---MainActivity";
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d(TAG, "MainActivity.onCreate.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button: {
                Intent intent = new Intent();
                intent.setAction("com.example.broadcasttest.MY_BROADCAST");
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent,null);
                break;
            }
            default:
                break;
        }
    }

    private class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            d(TAG, "NetworkChangeReceiver.onReceive.");
            ConnectivityManager connManager = (ConnectivityManager)getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
            if (networkInfo!=null && networkInfo.isAvailable()){
                Toast.makeText(context,"Network is available",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"Network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
