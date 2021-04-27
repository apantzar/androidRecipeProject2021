package com.pantz.recipepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecipeDatabase extends SQLiteOpenHelper {




    //////////////////////////////////////////////////
    /////////////////////INFO////////////////////////
    ////////////////////////////////////////////////


    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "bizRecipe";

    ///////////////////////////////////////////////////
    /////////////////////COLUMNS///////////////////////
    //////////////////////////////////////////////////
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_TITLE ="recipe_title";
    private static final String COLUMN_CATEGORY ="recipe_category";
    private static final String COLUMN_BASIC_ELEMENT="basic_element";
    private static final String COLUMN_ELEMENTS = "elements";
    private static final String COLUMN_EXEC = "exec";
    private static final String COLUMN_CALORIES ="calories";
    private static final String COLUMN_SPECIALD="special_d";
    private static final String COLUMN_DATE_ADDED="date_added";
    private static final String COLUMN_EXEC_TIME="exec_time";
    private static final String COLUMN_DIF_RATE="dif_rate";




    public RecipeDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    /***
     * Creates the database schema
     * @author apantzar
     * @param db the database
     */

    @Override
    public void onCreate(SQLiteDatabase db) {


        String theQuery = "CREATE TABLE "+ TABLE_NAME+
                " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                COLUMN_BASIC_ELEMENT +" TEXT NOT NULL,"     +
                COLUMN_TITLE         +" TEXT NOT NULL,"     +
                COLUMN_CALORIES      +" INTEGER NOT NULL,"  +
                COLUMN_DATE_ADDED    +" DATE NOT NULL,"     +
                COLUMN_CATEGORY      +" TEXT NOT NULL,"     +
                COLUMN_DIF_RATE      +" INTEGER NOT NULL,"  +
                COLUMN_EXEC_TIME     +" INTEGER NOT NULL,"  +
                COLUMN_SPECIALD      +" TEXT NOT NULL,"     +
                COLUMN_ELEMENTS      +" TEXT NOT NULL,"     +
                COLUMN_EXEC          +" TEXT NOT NULL);";



        db.execSQL(theQuery); //To run theQuery


    }

    /**
     * To upgrade the database
     *   -> deletes table if exists (just to avoid errors)
     *
     *   -> the onCreate method will create the table again
     *
     *   (WARNING :: This will be harmful)
     *
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME); //in order to delete the table
        onCreate(db); // to create again
    }
}
