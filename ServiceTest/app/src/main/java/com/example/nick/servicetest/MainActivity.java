package com.example.nick.servicetest;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "nick-MainActivity";
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected.");
            downloadBinder = (MyService.DownloadBinder)service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected.");
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = (Button)findViewById(R.id.start_service);
        stopService = (Button)findViewById(R.id.stop_service);
        bindService = (Button)findViewById(R.id.bind_service);
        unbindService = (Button)findViewById(R.id.unbind_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:{
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
                break;
            case R.id.stop_service:{
                Intent intent = new Intent(this,MyService.class);
                stopService(intent);
            }

                break;
            case R.id.bind_service:{
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
            }
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default:
                break;

        }
    }
}
