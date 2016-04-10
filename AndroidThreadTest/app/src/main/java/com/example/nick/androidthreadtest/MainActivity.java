package com.example.nick.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    private static final int UPDATE_TEXT = 1;
    private TextView text;
    private Button changeText;
    private Handler handler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text_id);
        changeText = (Button)findViewById(R.id.change_text);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what==UPDATE_TEXT){
                    text.setText("Nice to meet you!");
                }
            }
        };
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       // text.setText("Nice to meet you!");
                        Message msg = Message.obtain();
                        msg.what=UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }


}
