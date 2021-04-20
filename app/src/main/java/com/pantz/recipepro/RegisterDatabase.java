package com.pantz.recipepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "register";


   //the columns that the table will have
   private static final String COLUMN_ID ="_id";
   private static final String COLUMN_USERNAME="username";
   private static final String COLUMN_PASSWORD="password";





    public RegisterDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    //this method is called the db is accessed. It creates the database
    @Override
    public void onCreate(SQLiteDatabase db) {

        //create the table
        String table = "CREATE TABLE "+ TABLE_NAME+
                " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                COLUMN_USERNAME +" TEXT NOT NULL,"     +
                COLUMN_PASSWORD         +" TEXT NOT NULL);";


        db.execSQL(table);
    }



    //this is called when you update the database (when the version of the db changes)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME); //in order to delete the table
        onCreate(db); // to create again
    }


    //method that adds elements to the table
    public boolean addElement(User user){
        SQLiteDatabase db = this.getWritableDatabase(); //to write in the db
        ContentValues content = new ContentValues();

        //associate the columns of the db with the inputs
        content.put(COLUMN_USERNAME, user.getUsername());
        content.put(COLUMN_PASSWORD, user.getPassword());

        //insert the inputs to the table of the db
        long insert = db.insert(TABLE_NAME, null, content);

        //it will return true/false. True if the insertion was successful. False if not.
        return insert != -1;
    }

    public Boolean uniqueUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + COLUMN_USERNAME + " = ?", new String[] {username});
        return cursor.getCount() > 0;

    }

    public Boolean usernamePasswordMatch(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ TABLE_NAME + " where " + COLUMN_USERNAME + " =?  and " + COLUMN_PASSWORD + "=?", new String[] {username, password});
        return cursor.getCount() > 0;
    }



}
