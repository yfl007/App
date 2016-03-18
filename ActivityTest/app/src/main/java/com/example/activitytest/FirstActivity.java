package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


public class FirstActivity extends Activity {

    private static final int REQUEST_CODE = 1;
    private static final String TAG = "Nick--FirstActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate:" + FirstActivity.this.toString());
        Log.d(TAG,"OnCreate:Taskid is "+getTaskId());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_layout);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(FirstActivity.this,"You clicked button1!",Toast.LENGTH_SHORT).show();
//                finish();
            Intent intent= new Intent(FirstActivity.this,SecondActivity.class);
//                Intent intent = new Intent("com.example.activitytest.ACTION_STARTUP");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:10086"));
//                intent.putExtra("extra_data","the name of button 2");
//                startActivity(intent);
//                startActivityForResult(intent,REQUEST_CODE);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
            if(resultCode==RESULT_OK){
                String s= data.getStringExtra("data_return");
                Toast.makeText(FirstActivity.this,s,Toast.LENGTH_LONG).show();
            }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You clicked add item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "You clicked remove item", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;/*super.onMenuItemSelected(featureId, item);*/
    }
}
