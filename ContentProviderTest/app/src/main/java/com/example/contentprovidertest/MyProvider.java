package com.example.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Nick on 2016/3/21.
 */
public class MyProvider extends ContentProvider {
    private static UriMatcher uriMatcher;
    private static final int TABLE1_DIR = 1;

    private static final int TABLE1_ITEM = 2;

    private static final int TABLE2_DIR = 3;

    private static final int TABLE2_ITEM = 4;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.contentprovidertest.provider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.contentprovidertest.provider","table1/#",TABLE1_ITEM);
        uriMatcher.addURI("com.example.contentprovidertest.provider","table2",TABLE2_DIR);
        uriMatcher.addURI("com.example.contentprovidertest.provider","table2/#",TABLE2_ITEM);
    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:

                break;
            case  TABLE1_ITEM:
                break;
            case  TABLE2_DIR:
                break;
            case TABLE2_ITEM:
                break;
            default :
                break;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentprovidertest.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item /vnd.com.example.contentprovidertest.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contentprovidertest.provider.table2";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contentprovidertest.provider.table2";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
