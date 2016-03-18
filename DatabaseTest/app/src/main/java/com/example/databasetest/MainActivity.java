package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.nio.charset.MalformedInputException;


public class MainActivity extends Activity {
    private static final String TAG = "Nick-MainActivity";
    MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"book.db",null,2);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("author","David");
                cv.put("price",25.0);
                cv.put("pages",508);
                cv.put("name","Beauty of code");
                db.insert("Book",null,cv);
                cv.clear();
                cv.put("author","Bruce");
                cv.put("price",30.5);
                cv.put("pages",490);
                cv.put("name","King of POP");
                db.insert("Book", null, cv);

            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put("price",20.0);
                db.update("Book", cv, "name=?", new String[]{"Beauty of code"});
            }
        });
        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "price > ?", new String[]{"20.0"});
            }
        });
        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor=  db.query("Book", null, "price>?", new String[]{"18.0"}, null, null, null);
                if (cursor.moveToFirst()){
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        Log.d(TAG, "MainActivity.query: id="+id +" name="+name+" author="
                                +author+" price="+price+" pages="+pages);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    db.delete("Book",null,null);
                    if (true){
                        throw new NullPointerException();
                    }
                    ContentValues values = new ContentValues();
                    values.put("author","Tom");
                    values.put("price",15.0);
                    values.put("pages",320);
                    values.put("name","C primary");
                    db.insert("Book",null,values);
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                }
            }
        });
    }

}
