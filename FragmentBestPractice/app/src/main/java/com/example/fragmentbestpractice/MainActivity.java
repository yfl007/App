package com.example.fragmentbestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import static android.util.Log.d;


public class MainActivity extends Activity {
    private static final String TAG = "Nick---MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        d(TAG, "MainActivity.onCreate.");
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

}
