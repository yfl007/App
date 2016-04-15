package com.example.networktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.util.Log.d;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final int SHOW_RESPONSE = 1;
    private static final String TAG = "nick--MainActivity";
    private Button sendRequest;
    private TextView response;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        response = (TextView)findViewById(R.id.response);
        sendRequest.setOnClickListener(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                if (msg.what==SHOW_RESPONSE){
                    String responseStr = (String)msg.obj;
                    d(TAG, "handleMessage response= "+responseStr);
                    response.setText(responseStr);
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_request:
                d(TAG, "onClick ");
                sendRequestWithHttpURLConnection();
                break;
            default:
                break;
        }
    }
    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection =null;
                try {
                    URL url = new URL("http://www.sina.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    InputStreamReader  inReader = new InputStreamReader(in);
                    BufferedReader bufferedReader = new BufferedReader(inReader);
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine())!=null){
                        builder.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what = SHOW_RESPONSE;
                    msg.obj = builder.toString();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}
