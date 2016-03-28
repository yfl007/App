package com.example.smstest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import java.util.Objects;

import static android.util.Log.d;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    private TextView sender;
    private TextView content;
    MessageReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d(TAG, "MainActivity.onCreate.");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        sender = (TextView)findViewById(R.id.sender);
        content = (TextView)findViewById(R.id.content);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(100);
        receiver = new MessageReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            d(TAG, "MessageReceiver.onReceive.");
            abortBroadcast();

            Bundle bundle  = intent.getExtras();
            Object[] smsObjects = (Object[])bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[smsObjects.length];
            int i=0,j=0;
            for (Object o:smsObjects){
                messages[i++] = SmsMessage.createFromPdu((byte[])o);
                d(TAG, "MessageReceiver.onReceive.msg==" + messages[j++].getDisplayMessageBody());
            }
            //获得短信发送人号码
            String address;
            address = messages[0].getOriginatingAddress();
            //获得短信内容
            String fullMessage ="";
            for (SmsMessage m:messages){
                fullMessage += m.getDisplayMessageBody();
            }
            sender.setText(address);
            content.setText(fullMessage);
        }
    }
}
