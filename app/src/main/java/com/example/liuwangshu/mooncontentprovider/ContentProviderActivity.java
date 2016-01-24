package com.example.liuwangshu.mooncontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContentProviderActivity extends AppCompatActivity {
    private final static String TAG = "ContentProviderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        Uri uri = Uri.parse("content://com.example.liuwangshu.mooncontentprovide.GameProvider");
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("_id", 2);
        mContentValues.put("name", "大航海时代ol");
        mContentValues.put("describe", "最好玩的航海网游");
        getContentResolver().insert(uri, mContentValues);
        Cursor gameCursor = getContentResolver().query(uri, new String[]{"name", "describe"}, null, null, null);
        while (gameCursor.moveToNext()) {
            Game mGame = new Game(gameCursor.getString(0), gameCursor.getString(1));
            Log.i(TAG, mGame.gameName + "---" + mGame.gameDescribe);
        }
    }
}
