package com.example.contentprovidertest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Nick on 2016/3/21.
 */
public class MyProvider extends ContentProvider {
    private static final String TAG = "Nick-MyProvider";
    private MyDatabaseHelper dbHelper;
    private static UriMatcher uriMatcher;
    private static final int BOOK_DIR = 1;

    private static final int BOOK_ITEM = 2;

    private static final int CATEGORY_DIR = 3;

    private static final int CATEGORY_ITEM = 4;

    private static final String AUTHORITY = "com.example.contentprovidertest.provider";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }
    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case  BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                Log.d(TAG, "MyProvider.query.bookId= "+bookId+" List= "+ uri.getPathSegments());
                cursor = db.query("Book",projection,"id = ?",new String[] { bookId },null,null,sortOrder);
                break;
            case  CATEGORY_DIR:
                cursor =db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case  CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id = ?",new String[] {categoryId},null,null,sortOrder);
                break;
            default :
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/"+AUTHORITY+".book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/"+AUTHORITY+".book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/"+AUTHORITY+".category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/"+AUTHORITY+".category";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn =null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRow = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deletedRow = db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRow = db.delete("Book","id = ? ",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deletedRow = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRow = db.delete("Category","id = ?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return deletedRow;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRow = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updatedRow = db.update("Book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId =  uri.getPathSegments().get(1);
                updatedRow = db.update("Book",values,"id = ? ",new String[] {bookId});
                break;
            case CATEGORY_DIR:
                updatedRow = db.update("Category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRow = db.update("Category", values, "id = ? ", new String[] {categoryId});
                break;
            default:
                break;

        }

        return updatedRow;
    }
}
