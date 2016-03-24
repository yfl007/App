package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button noticeButton = (Button)findViewById(R.id.notice);
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this,1,
                        new Intent(MainActivity.this,NotificationActivity.class),PendingIntent.FLAG_CANCEL_CURRENT);
                Notification notification = new Notification.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.notification)
                        .setTicker("This is a ticker")
                        .setWhen(System.currentTimeMillis())
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setContentIntent(pi)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .build();
                manager.notify(1,notification);

            }
        });
    }

}
