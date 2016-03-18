package com.example.uiwidgettest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener{
    private static final String TAG = "nick---MainActivity";
    private Button button;
    private Button button1;
    private EditText editText;
    private ImageView imgView;
    private ProgressBar progressBar;
    private int progress =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate:this="+this.toString());
        Log.d(TAG, "onCreate:MainActivity.this=" + MainActivity.this.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        editText = (EditText)findViewById(R.id.edit_text);
        imgView = (ImageView)findViewById(R.id.img_view);
        imgView.setImageResource(R.drawable.cat);
//        progressBar =(ProgressBar)findViewById(R.id.progress_bar);
        
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
//                String editTextString = editText.getText().toString();
//                Toast.makeText(this,editTextString,Toast.LENGTH_SHORT).show();
/*                if(imgView.getVisibility()==View.VISIBLE){
                    imgView.setVisibility(View.GONE);
                }else{
                    imgView.setVisibility(View.VISIBLE);
                }*/
//                progress=progress+10;
//                progressBar.setProgress(progress);
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setPositiveButton(R.string.ok,this)
                        .setNegativeButton(R.string.cancel,this)
                        .create();
                alertDialog.setTitle("This a dialog title");
                alertDialog.setMessage("This a dialog message");
                alertDialog.show();
                break;
            case R.id.button1:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("This is progress dialog title");
                progressDialog.setMessage("loading......");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d(TAG, "onClick:which=" + which);
        switch(which){
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(this,"You've confirmed it",Toast.LENGTH_SHORT).show();

                break;
            case DialogInterface.BUTTON_NEGATIVE:
                Toast.makeText(this,"You've canceled it",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
