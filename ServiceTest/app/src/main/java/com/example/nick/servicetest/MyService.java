package com.example.nick.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.net.BindException;

/**
 * Created by Yefenglin on 2016/4/10.
 */
public class MyService extends Service {

    DownloadBinder mBinder = new DownloadBinder();
    private static final String TAG = "nick-MyService";
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind.");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate.");
        PendingIntent pi = PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
        Notification notification = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification)
                .setTicker("This is ticker")
                .setContentTitle("This is a service")
                .setContentText("This is a service content")
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand.");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy.");
    }
    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d(TAG, "startDownload.");
        }
        public int getProgress(){
            Log.d(TAG, "getProgress.");
            return 0;
        }
    }
}
