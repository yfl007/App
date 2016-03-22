package com.example.testcontentprovider;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button insertButton = (Button)findViewById(R.id.insert_button);
        insertButton.setOnClickListener(this);
        Button deleteButton = (Button)findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);
        Button updateButton = (Button)findViewById(R.id.update_button);
        updateButton.setOnClickListener(this);
        Button queryButton = (Button)findViewById(R.id.query_button);
        queryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
