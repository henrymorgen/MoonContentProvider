package com.example.liuwangshu.mooncontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class GameProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.liuwangshu.mooncontentprovide.GameProvider";
    public static final Uri GAME_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/game");
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private SQLiteDatabase mDb;
    private Context mContext;
    private String table;

    static {
        mUriMatcher.addURI(AUTHORITY, "game", 0);
    }

    @Override
    public boolean onCreate() {
        table = DbOpenHelper.GAME_TABLE_NAME;
        mContext = getContext();
        initProvoder();
        return false;
    }

    private void initProvoder() {
        mDb = new DbOpenHelper(mContext).getWritableDatabase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDb.execSQL("delete from " + DbOpenHelper.GAME_TABLE_NAME);
                mDb.execSQL("insert into game values(1,'九阴真经ol','最好玩的武侠网游');");
            }
        }).start();
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String table = DbOpenHelper.GAME_TABLE_NAME;
        Cursor mCursor = mDb.query(table, projection, selection, selectionArgs, null, sortOrder, null);
        return mCursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDb.insert(table, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
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
