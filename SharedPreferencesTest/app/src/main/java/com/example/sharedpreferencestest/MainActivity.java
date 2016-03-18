package com.example.sharedpreferencestest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("test",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "Nick Ye");
                editor.putInt("age", 33);
                editor.commit();
            }
        });
        Button button1 = (Button)findViewById(R.id.restore);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                int age;
                SharedPreferences sharedPreferences = getSharedPreferences("test",MODE_PRIVATE);
                name = sharedPreferences.getString("name","xxx");
                age = sharedPreferences.getInt("age", 0);
                Log.d(TAG, "MainActivity.onClick.name=" + name+",age="+age);
            }
        });
    }

}
