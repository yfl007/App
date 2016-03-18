package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;


public class ThirdActivity extends Activity {

    private static final String TAG ="nick -- ThirdActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate:"+ThirdActivity.this.toString());
        Log.d(TAG, "OnCreate:Taskid is " + getTaskId());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.third_layout);
    }

}
