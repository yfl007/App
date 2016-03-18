package com.example.shareprefencesbestpractice;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.util.Log.d;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "Nick-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                d(TAG, "MainActivity.onClick"+" Button is clicked");
                Intent intent = new Intent("com.example.broadcastbestpractice.FORCE_OFFLINE_BROADCAST");
                sendBroadcast(intent);
                break;
            }
            default:
                break;
        }
    }
}
