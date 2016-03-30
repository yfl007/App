package com.example.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.util.Log.d;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    private TextView sender;
    private TextView content;
    MessageReceiver receiver;
    EditText inputNum;
    EditText inputContent;
    Button send;
    private SendStatusReceiver sendStatusReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d(TAG, "MainActivity.onCreate.");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        接受短信部分
        sender = (TextView)findViewById(R.id.sender);
        content = (TextView)findViewById(R.id.content);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(100);
        receiver = new MessageReceiver();
        registerReceiver(receiver,intentFilter);
//        发送短信部分
        inputNum = (EditText)findViewById(R.id.input_num);
        inputContent = (EditText)findViewById(R.id.input_content);
        IntentFilter sendIntentFilter = new IntentFilter("SENT_SMS_ACTION");
        sendStatusReceiver = new SendStatusReceiver();
        registerReceiver(sendStatusReceiver,sendIntentFilter);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager manager = SmsManager.getDefault();
                Intent intent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                manager.sendTextMessage(inputNum.getText().toString(),null,inputContent.getText().toString(),pi,null);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(sendStatusReceiver);
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

    private class SendStatusReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode()==RESULT_OK){
                makeText(context, "sent succeeded!", LENGTH_SHORT).show();
            }else {
                makeText(context, "sent failed!", LENGTH_SHORT).show();
            }
        }
    }
}
