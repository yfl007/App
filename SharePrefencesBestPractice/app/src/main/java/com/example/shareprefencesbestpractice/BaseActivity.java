package com.example.shareprefencesbestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Nick on 2016/3/3.
 */
public class BaseActivity extends Activity {
    private static final String TAG = "Nick---BaseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Log.d(TAG, "BaseActivity.onCreate.this=="+this.toString());
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
