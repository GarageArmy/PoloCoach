package com.example.adam.polocoach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016. 11. 29..
 */
public class DataBase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "Activities";
    // Contacts table name
    // Shops Table Columns names
    private static final String TABLE_ACTIVITIES = "table_activities";
    private static final String TEAM ="team";
    private static final String ID ="id";
    private static final String PLAYER_NAME = "name";
    private static final String ACTIVITY_NAME= "activity";
    private static final String ACTIVITY_TEXT= "activityText";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ACTIVITIES + "("
        + ID + " INTEGER PRIMARY KEY," +
                TEAM + " TEXT," +
                PLAYER_NAME + " TEXT," +
                ACTIVITY_NAME + " TEXT," +
                ACTIVITY_TEXT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
// Creating tables again
        onCreate(db);
    }

    public void addActivity(ActivityObject obj) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEAM, obj.getTeam());
        values.put(ACTIVITY_NAME, obj.getActivityName());
        values.put(ACTIVITY_TEXT, obj.getActivityText());
        values.put(PLAYER_NAME, obj.getPlayer());


        db.insert(TABLE_ACTIVITIES, null, values);
        db.close(); // Closing database connection
    }

    public ActivityObject getActivity(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITIES, new String[] { TEAM,
                        PLAYER_NAME, ACTIVITY_NAME, ACTIVITY_TEXT }, TEAM + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ActivityObject contact = new ActivityObject(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
// return Activity
        return contact;
    }

    public List<ActivityObject> getAllActivity() {
        List<ActivityObject> objList = new ArrayList<ActivityObject>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_ACTIVITIES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ActivityObject object = new ActivityObject();
                object.setId(Integer.parseInt(cursor.getString(0)));
                object.setTeam(cursor.getString(1));
                object.setPlayer(cursor.getString(2));
                object.setActivityName(cursor.getString(3));
                object.setActivityText(cursor.getString(4));
// Adding contact to list
                objList.add(object);
            } while (cursor.moveToNext());
        }
// return contact list
        return objList;
    }


   /* public List<ActivityObject> getDistinct(String by) {
        List<ActivityObject> objList = new ArrayList<ActivityObject>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(true, "table_activities", new String[] { TEAM,
                PLAYER_NAME, ACTIVITY_NAME, ACTIVITY_TEXT }, null, null, by, null, null, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ActivityObject object = new ActivityObject();
                object.setId(Integer.parseInt(cursor.getString(0)));
                object.setTeam(cursor.getString(1));
                object.setPlayer(cursor.getString(2));
                object.setActivityName(cursor.getString(3));
                object.setActivityText(cursor.getString(4));
// Adding contact to list
                objList.add(object);
            } while (cursor.moveToNext());
        }
// return contact list
        return objList;
    }*/


}