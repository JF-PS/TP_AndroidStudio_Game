package com.example.tp_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "sokoban.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderLevel.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderLevel.FeedEntry.COLUMN_ID + " INTEGER," +
                    FeedReaderLevel.FeedEntry.COLUMN_NAME + " TEXT," +
                    FeedReaderLevel.FeedEntry.COLUMN_ROWS + " INTEGER," +
                    FeedReaderLevel.FeedEntry.COLUMN_COLS + " INTEGER," +
                    FeedReaderLevel.FeedEntry.COLUMN_RECORD_TIME + " TEXT," +
                    FeedReaderLevel.FeedEntry.COLUMN_DIFFICULTY + " TEXT," +
                    FeedReaderLevel.FeedEntry.COLUMN_ACHIEVED + " BOOLEAN)";

    private static final String SQL_CREATE_ENTRIES_2 =
            "CREATE TABLE " + FeedReaderBoard.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderBoard.FeedEntry.COLUMN_ID + " INTEGER," +
                    FeedReaderBoard.FeedEntry.COLUMN_ROWS + " TEXT)";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderLevel.FeedEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_2 =
            "DROP TABLE IF EXISTS " + FeedReaderBoard.FeedEntry.TABLE_NAME;


    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        System.out.println("On insert les données.");
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_2);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        //db.execSQL(SQL_DELETE_ENTRIES_2);
        onCreate(db);
    }

    public void reset(SQLiteDatabase db) {
        System.out.println("On supprime les données.");
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_2);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}