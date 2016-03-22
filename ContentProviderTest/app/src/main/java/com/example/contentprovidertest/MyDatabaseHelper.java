package com.example.contentprovidertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nick on 2016/3/22.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final java.lang.String CREATE_BOOK = "create table Book ("+
            "id integer primary key autoincrement,"+
            "name text,"+
            "author text,"+
            "pages integer,"+
            "price real)";
    private static final String CREATE_CATEGORY = "create table Category ("+
            "id integer primary key autoincrement,"+
            "category_name text,"+
            "category_code integer)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
                break;
            default:
                break;
        }

    }
}
