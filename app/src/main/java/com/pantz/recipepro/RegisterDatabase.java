package com.pantz.recipepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RegisterDatabase extends SQLiteOpenHelper {




    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "bizRecipe";


   //add your columns here





    public RegisterDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }








}
