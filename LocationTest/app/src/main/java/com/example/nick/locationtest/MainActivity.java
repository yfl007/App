package com.example.nick.locationtest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MainActivity extends Activity {
    private static final String TAG = "nick-MainActivity";
    private static final int SHOW_RESPONSE = 1;
    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionTextView = (TextView)findViewById(R.id.position_textview);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
        }else if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(MainActivity.this, "There isn't Location provider", Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location!=null){
            showLocation(location);
        }

        locationManager.requestLocationUpdates(provider, 2000, 2, locationListener);

    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private void showLocation(final Location location) {
        String currentPosition = "Latitude is "+location.getLatitude()+"\n"
                +"Longitude is "+location.getLongitude();
//        positionTextView.setText(currentPosition);
        new Thread(new Runnable() {
            HttpURLConnection connection = null;
            @Override
            public void run() {
                StringBuilder addr = new StringBuilder();
                addr.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
                addr.append(location.getLatitude()).append(",");
                addr.append(location.getLongitude());
                addr.append("&sensor=false");
                try {
                    URL url = new URL(addr.toString());
                    Log.d(TAG, "run.addr: "+addr.toString());
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream is = connection.getInputStream();
                    InputStreamReader inReader = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(inReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        sb.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what = SHOW_RESPONSE;
                    msg.obj = sb.toString();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            if (msg.what==SHOW_RESPONSE){
                try {
                    JSONObject jsonObject = new JSONObject((String)msg.obj);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    if (jsonArray.length()>0){
                        JSONObject subObject = jsonArray.getJSONObject(0);
                        String address = subObject.getString("formatted_address");
                        positionTextView.setText(address);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager!=null){
            locationManager.removeUpdates(locationListener);
        }
    }
}
