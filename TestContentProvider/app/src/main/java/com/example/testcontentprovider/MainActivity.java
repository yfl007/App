package com.example.testcontentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "nick-MainActivity";
    private ContentResolver resolver;
    String bookId;
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
        resolver = getContentResolver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert_button: {
                Uri uri = Uri.parse("content://com.example.contentprovidertest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 456);
                values.put("price", 34.5);
                Uri newUri = resolver.insert(uri, values);
                values.clear();
                values.put("name", "Beauty of Code");
                values.put("author", "Tom");
                values.put("pages", 560);
                values.put("price", 80.0);
                newUri = resolver.insert(newUri,values);
                bookId = newUri.getPathSegments().get(1);
                Log.d(TAG, "onClick.bookId:" + bookId);
            }
                break;
            case R.id.delete_button:{
                Uri uri = Uri.parse("content://com.example.contentprovidertest.provider/book");
                int row = resolver.delete(uri,"author=?",new String[] {"Tom"});
                Log.d(TAG, "onClick.deletedRow="+row);
            }
                break;
            case R.id.update_button: {
                Uri uri = Uri.parse("content://com.example.contentprovidertest.provider/book");
                ContentValues values = new ContentValues();
                values.put("price",30.0);
                int updatedRow = resolver.update(uri,values,"price=?",new String[] {"34.5"});
                Log.d(TAG, "onClick.updatedRow="+updatedRow);
            }
                break;
            case R.id.query_button:{
                Uri uri = Uri.parse("content://com.example.contentprovidertest.provider/book");
                Cursor cursor = resolver.query(uri,null,null,null,null);
                if (cursor!=null){
                    while (cursor.moveToNext()){
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "onClick.id = "+id+" name = "+name+" author = "+author+" pages = "+pages+" price = "+price);
                    }
                }
            }

                break;
            default:
                break;
        }
    }
}
