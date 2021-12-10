package com.example.moviedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MovieDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Movie.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+ MovieContract.MovieEntry.TABLE_NAME + " ("+
                    MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY,"+
                    MovieContract.MovieEntry.COLUMN_NAME_ID_FILM+" TEXT)";
    //+ FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ MovieContract.MovieEntry.TABLE_NAME;

    public MovieDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){ db.execSQL(SQL_CREATE_ENTRIES);}
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}
