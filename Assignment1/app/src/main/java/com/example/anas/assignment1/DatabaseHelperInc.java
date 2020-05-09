package com.example.anas.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperInc extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "IncManager.db";

    private static final String TABLE_NAME = "user";

    private static final String COLUMNN_USER_ID = "user_id";
    private static final String COLUMNN_NAME = "user_name";
    private static final String COLUMNN_DATE = "date";
    private static final String COLUMNN_TITLE = "title";
    private static final String COLUMNN_CATEGORY = "category";
    private static final String COLUMNN_AMOUNT = "amount";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    " user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMNN_NAME + " TEXT," +
                    COLUMNN_DATE + " TEXT," +
                    COLUMNN_TITLE + " TEXT," +
                    COLUMNN_CATEGORY + " TEXT," +
                    COLUMNN_AMOUNT + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelperInc(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL( CREATE_TABLE );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    public boolean addData(String name, String date, String category, String title, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(  );
        contentValues.put( COLUMNN_NAME, name );
        contentValues.put( COLUMNN_DATE,date );
        contentValues.put( COLUMNN_AMOUNT,amount );
        contentValues.put( COLUMNN_CATEGORY, category );
        contentValues.put( COLUMNN_TITLE, title );
        long result = db.insert( TABLE_NAME,null,contentValues );
        if(result==-1){
            return false;
        }
        else{
            return true;
        }

    }
    public Cursor getIncome(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery( "SELECT * FROM " + TABLE_NAME, null );
        return data;
    }
}
