package com.example.networktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

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
                d(TAG, "handleMessage response= " + responseStr);
                response.setText(responseStr);
//                1.用Pull解析xml格式文件
//                parseXMLWithPull(responseStr);
//                2.用Sax解析xml格式文件
//                parseXMLWithSax(responseStr);
//                3.用JSONObject解析json格式文件
                parseJSONWithJSONObject(responseStr);
            }
        }
    };
}


    private void parseJSONWithJSONObject(String responseStr) {
        try {
            JSONArray jsonArray = new JSONArray(responseStr);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                String id = object.getString("id");
                String name = object.getString("name");
                String version = object.getString("version");
                Log.d(TAG, "parseJSONWithJSONObject.id== "+id);
                Log.d(TAG, "parseJSONWithJSONObject.name== "+name);
                Log.d(TAG, "parseJSONWithJSONObject.version== "+version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithSax(String responseStr) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader reader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler = new ContentHandler();
            reader.setContentHandler(contentHandler);
            reader.parse(new InputSource(new StringReader(responseStr)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String xmlStr) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlStr));
            int eventType = parser.getEventType();
            String id = "";
            String name ="";
            String version = "";
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName = parser.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:{
                     if ("id".equals(nodeName)){
                         id = parser.nextText();
                     }else if ("name".equals(nodeName)){
                         name = parser.nextText();
                     }else if ("version".equals(nodeName)){
                         version = parser.nextText();
                     }
                    }
                    break;
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodeName)){
                            Log.d(TAG, "parseXMLWithPull.id = "+id);
                            Log.d(TAG, "parseXMLWithPull.name = "+name);
                            Log.d(TAG, "parseXMLWithPull.version = "+version);
                        }
                        break;

                    }
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
//                  URL url = new URL("http://www.sina.com");
//                    URL url = new URL("http://192.168.56.1/get_data.xml");
                    URL url = new URL("http://192.168.56.1/get_data.json");
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
