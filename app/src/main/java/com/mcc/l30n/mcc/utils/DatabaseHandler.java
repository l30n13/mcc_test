package com.mcc.l30n.mcc.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mcc.l30n.mcc.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 27/3/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mcc_profile";

    private static final String TABLE_PROFILE = "profile";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PROFILE_IMAGE = "profile_image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TARGET_TABLE = "CREATE TABLE " + TABLE_PROFILE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_AGE + " INTEGER,"
                + KEY_PHONE_NUMBER + " TEXT,"
                + KEY_PROFILE_IMAGE + " TEXT" + ")";

        db.execSQL(CREATE_TARGET_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addProfile(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, profile.getName());
        values.put(KEY_AGE, profile.getAge());
        values.put(KEY_PHONE_NUMBER, profile.getPhoneNumber());
        values.put(KEY_EMAIL, profile.getEmail());
        values.put(KEY_PROFILE_IMAGE, profile.getImage());
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    public List<Profile> getAllProfile() {
        List<Profile> profiles = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile visit = new Profile(
                        cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(KEY_PROFILE_IMAGE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getInt(cursor.getColumnIndex(KEY_AGE))
                );
                profiles.add(visit);
            } while (cursor.moveToNext());
        }
        // return visit list
        return profiles;
    }
}
