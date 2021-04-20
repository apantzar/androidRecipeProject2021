package com.pantz.recipepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RegisterDatabase extends SQLiteOpenHelper {




    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "register";


   //add your columns here
   private static final String COLUMN_ID ="_id";
   private static final String COLUMN_USERNAME="username";
   private static final String COLUMN_PASSWORD="password";





    public RegisterDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }








}
