package com.example.uicustomview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.util.Log.d;


/**
 * TODO: document your custom view class.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "nick--TitleLayout";

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        d(TAG, "TitleLayout.TitleLayout.context=" + context.toString());
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button backButton = (Button)findViewById(R.id.back_button);
        Button editButton = (Button)findViewById(R.id.edit_button);
        backButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                d(TAG, "TitleLayout.onClick.getContext()="+((Activity)getContext()).toString());
                ((Activity)getContext()).finish();
                break;
            case R.id.edit_button:
                d(TAG, "TitleLayout.onClick.edit_button.getContext()="+getContext());
                Toast.makeText(getContext(),"You clicked Edit button",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
