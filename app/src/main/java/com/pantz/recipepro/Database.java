package com.pantz.recipepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 1;


    private static final String REGISTER_TABLE_NAME = "register";
    private static final String REGISTER_COLUMN_ID ="_id";
    private static final String REGISTER_COLUMN_USERNAME="username";
    private static final String REGISTER_COLUMN_PASSWORD="password";


    public static final String RECIPE_TABLE_NAME = "bizRecipe";
    private static final String RECIPE_COLUMN_ID ="_id";
    private static final String RECIPE_COLUMN_TITLE ="recipe_title";
    private static final String RECIPE_COLUMN_CATEGORY ="recipe_category";
    private static final String RECIPE_COLUMN_BASIC_ELEMENT="basic_element";
    private static final String RECIPE_COLUMN_ELEMENTS = "elements";
    private static final String RECIPE_COLUMN_EXEC = "exec";
    private static final String RECIPE_COLUMN_CALORIES ="calories";
    private static final String RECIPE_COLUMN_SPECIALD="special_d";
    private static final String RECIPE_COLUMN_DATE_ADDED="date_added";
    private static final String RECIPE_COLUMN_EXEC_TIME="exec_time";
    private static final String RECIPE_COLUMN_DIF_RATE="dif_rate";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String table = "CREATE TABLE "+ REGISTER_TABLE_NAME+
                " ("+ REGISTER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                REGISTER_COLUMN_USERNAME +" TEXT NOT NULL,"     +
                REGISTER_COLUMN_PASSWORD         +" TEXT NOT NULL);";


        db.execSQL(table);



        String theQuery = "CREATE TABLE "+ RECIPE_TABLE_NAME+
                " ("+ RECIPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                RECIPE_COLUMN_BASIC_ELEMENT +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_TITLE         +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_CALORIES      +" INTEGER NOT NULL,"  +
                RECIPE_COLUMN_DATE_ADDED    +" DATE NOT NULL,"     +
                RECIPE_COLUMN_CATEGORY      +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_DIF_RATE      +" INTEGER NOT NULL,"  +
                RECIPE_COLUMN_EXEC_TIME     +" INTEGER NOT NULL,"  +
                RECIPE_COLUMN_SPECIALD      +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_ELEMENTS      +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_EXEC          +" TEXT NOT NULL);";

        db.execSQL(theQuery); //To run theQuery

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ REGISTER_TABLE_NAME); //in order to delete the table
        db.execSQL("DROP TABLE IF EXISTS "+ RECIPE_TABLE_NAME); //in order to delete the table
        onCreate(db); // to create again
    }




    //method that adds elements to the table
    public boolean addElement(User user){
        SQLiteDatabase db = this.getWritableDatabase(); //to write in the db
        ContentValues content = new ContentValues();

        //associate the columns of the db with the inputs
        content.put(REGISTER_COLUMN_USERNAME, user.getUsername());
        content.put(REGISTER_COLUMN_PASSWORD, user.getPassword());

        //insert the inputs to the table of the db
        long insert = db.insert(REGISTER_TABLE_NAME, null, content);

        //it will return true/false. True if the insertion was successful. False if not.
        return insert != -1;
    }

    public Boolean uniqueUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + REGISTER_TABLE_NAME + " where " + REGISTER_COLUMN_USERNAME + " = ?", new String[] {username});
        return cursor.getCount() > 0;

    }

    public Boolean usernamePasswordMatch(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ REGISTER_TABLE_NAME + " where " + REGISTER_COLUMN_USERNAME + " =?  and " + REGISTER_COLUMN_PASSWORD + "=?", new String[] {username, password});
        return cursor.getCount() > 0;
    }
}