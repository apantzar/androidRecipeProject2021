package com.pantz.recipepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Recipe.db";
    private static final int DATABASE_VERSION = 3;

    public static final String IMAGES_TABLE_NAME = "images";
    public static final String IMAGES_COLUMN_ID = "id";
    public static final String IMAGES_COLUMN_RECIPE_ID = "recipe_id";


    /**
     * For FAVORITE
     */
    public static  final String FAV_TABLE_NAME="favorite_list";
    private static final String FAV_ID ="id";
    private static final String FAV_TITLE ="fav_title";



    public static final String REGISTER_TABLE_NAME = "register";
    public static final String REGISTER_COLUMN_ID ="_id";
    public static final String REGISTER_COLUMN_USERNAME="username";
    public static final String REGISTER_COLUMN_PASSWORD="password";


    public static final String RECIPE_TABLE_NAME = "bizRecipe";
    private static final String RECIPE_COLUMN_ID ="_id";
    private static final String RECIPE_COLUMN_TITLE ="recipe_title";
    private static final String RECIPE_COLUMN_CATEGORY ="recipe_category";
    private static final String RECIPE_COLUMN_BASIC_ELEMENT="basic_element";
    private static final String RECIPE_COLUMN_ELEMENTS = "_elements";
    private static final String RECIPE_COLUMN_EXEC = "exec";
    private static final String RECIPE_COLUMN_CALORIES ="calories";
    private static final String RECIPE_COLUMN_SPECIALD="special_d";
    private static final String RECIPE_COLUMN_DATE_ADDED="date_added";
    private static final String RECIPE_COLUMN_EXEC_TIME="exec_time";
    private static final String RECIPE_COLUMN_DIF_RATE="dif_rate";
    private  static final String IMAGES_COLUMN_PATH = "path";



    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    /**
     * Table for images (image id, path, recipe id->(as foreign_key)
     * @param db the database
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
   //     String imagesTable = "CREATE TABLE " + IMAGES_TABLE_NAME+ " ("+ IMAGES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
  //              IMAGES_COLUMN_RECIPE_ID + "INTEGER, " + IMAGES_COLUMN_PATH +" TEXT NOT NULL);";

   //     db.execSQL(imagesTable);

        String table = "CREATE TABLE "+ REGISTER_TABLE_NAME+
                " ("+ REGISTER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                REGISTER_COLUMN_USERNAME +" TEXT NOT NULL,"     +
                REGISTER_COLUMN_PASSWORD         +" TEXT NOT NULL);";



        String favTable = "CREATE TABLE "+ FAV_TABLE_NAME+
                " ("+ FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                FAV_TITLE +" TEXT NOT NULL);";




        db.execSQL(table);
        db.execSQL(favTable);





        String theQuery = "CREATE TABLE "+ RECIPE_TABLE_NAME+
                " ("+ /*ok*/ RECIPE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                RECIPE_COLUMN_BASIC_ELEMENT +" TEXT NOT NULL,"     +
                /*ok*/   RECIPE_COLUMN_TITLE         +" TEXT NOT NULL,"     +
                /**/   RECIPE_COLUMN_CALORIES      +" INTEGER ,"  +
                /*will be deleted*/ RECIPE_COLUMN_DATE_ADDED    +" DATE ,"     +
                RECIPE_COLUMN_CATEGORY      +" TEXT NOT NULL,"     +
                RECIPE_COLUMN_DIF_RATE      +" INTEGER ,"  +
                /**/  RECIPE_COLUMN_EXEC_TIME     +" INTEGER ,"  +
                RECIPE_COLUMN_SPECIALD      +" TEXT NOT NULL,"     +
                /*ok*/   RECIPE_COLUMN_ELEMENTS      +" TEXT NOT NULL,"     +
                /*ok*/  RECIPE_COLUMN_EXEC          +" TEXT NOT NULL,"    +
                IMAGES_COLUMN_PATH +" TEXT );";


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
        db.execSQL("DROP TABLE IF EXISTS "+ FAV_TABLE_NAME); //in order to delete the table
        //db.execSQL("ALTER TABLE bizRecipe  ADD COLUMN path TEXT NOT NULL");
       // db.execSQL("DROP TABLE IF EXISTS "+ IMAGES_TABLE_NAME); //in order to delete the table FOR IMAGES
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


    /**
     * Checks id the favorite title is already in db
     * @param title title of the recipe
     * @return true/false
     */

    public Boolean uniqueFavTitle(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + FAV_TABLE_NAME + " where " + FAV_TITLE + " = ?", new String[] {title});
        return cursor.getCount() > 0;

    }




    public Boolean usernamePasswordMatch(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+ REGISTER_TABLE_NAME + " where " + REGISTER_COLUMN_USERNAME + " =?  and " + REGISTER_COLUMN_PASSWORD + "=?", new String[] {username, password});
        return cursor.getCount() > 0;
    }


    /**
     * From JSON to the database table
     *
     * @authors: atzanako, apantzar
     *
     * @param jid -> the ID of each recipe
     * @param jRecipeTitle -> Recipe Title
     * @param jRecipeCat -> Category
     * @param jBasicElement -> Basic element
     * @param jExec -> Time to exec
     * @param jCalories -> Calories
     * @param jSpecialD -> Special Diet
     * @param jDate -> date added
     * @param jExecTime-> sum of time
     * @param jDifRate-> rate
     */


    public void writeJSONtoTheDB(int jid, String jRecipeTitle, String jRecipeCat, String jBasicElement,
                                 String jElements, String jExec, int jCalories, String jSpecialD,
                                 Date jDate, int jExecTime, int jDifRate,String jImagePath ){

        SQLiteDatabase db = this.getWritableDatabase(); //to write in the db
        ContentValues content = new ContentValues();

        content.put(RECIPE_COLUMN_ID, jid);
        content.put(RECIPE_COLUMN_TITLE, jRecipeTitle);
        content.put(RECIPE_COLUMN_CATEGORY, jRecipeCat);
        content.put(RECIPE_COLUMN_BASIC_ELEMENT, jBasicElement);
        content.put(RECIPE_COLUMN_ELEMENTS, jElements);
        content.put(RECIPE_COLUMN_EXEC, jExec);
        content.put(RECIPE_COLUMN_CALORIES, jCalories);
        content.put(RECIPE_COLUMN_SPECIALD, jSpecialD);
        content.put(RECIPE_COLUMN_DATE_ADDED, String.valueOf(jDate));
        content.put(RECIPE_COLUMN_EXEC_TIME, jExecTime);
        content.put(RECIPE_COLUMN_DIF_RATE, jDifRate);
        content.put(IMAGES_COLUMN_PATH, jImagePath );




        try{
            //insert the inputs to the table of the db
            db.insert(RECIPE_TABLE_NAME, null, content);
        }catch (SQLException e){
            e.printStackTrace();
        }




    }


    /**
     *
     *
     * @param title -> recipe's title
     */

    public void addFavToDatabaseNow( String title){


        if(!uniqueFavTitle(title)){
            SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
            ContentValues values = new ContentValues();

            values.put(FAV_TITLE, title);

            sqLiteDatabase.insert(FAV_TABLE_NAME, null, values);
        }

    }


    public void deleteFromFav(String title){
      /*  try{
            SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
            sqLiteDatabase.execSQL("delete from "+FAV_TABLE_NAME+" where fav_title="+title);
        }catch (SQLException e){
            System.out.println("-------From delete-------");
            e.printStackTrace();
        }*/


        try{
            SQLiteDatabase db = getWritableDatabase();
            String delete = "delete from "+FAV_TABLE_NAME+" where fav_title='"+title+"'";
            Cursor mcursor = db.rawQuery(delete, null);
            mcursor.moveToFirst();
        }catch (SQLException e){
            e.printStackTrace();
        }



    }



    /**
     * In order to execute the SQL query and select the data
     * @return selected data with cursor
     */

    Cursor readTheData(String string){
        String theQuery=string+RECIPE_TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=null;
        if(sqLiteDatabase!=null){
            cursor = sqLiteDatabase.rawQuery(theQuery, null);
        }
        return cursor;
    }



    public String getExec(String query, String column){
        String txtExec="";
        Cursor cursor = getReadableDatabase().rawQuery(query,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            txtExec = cursor.getString(cursor.getColumnIndex(column));
            cursor.moveToNext();
        }

        cursor.close();
        return txtExec;
    }


    public String[] getData(String query, String column ){
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        ArrayList<String> recipes = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            recipes.add(cursor.getString(cursor.getColumnIndex(column)));
            cursor.moveToNext();
        }
        cursor.close();
        return recipes.toArray(new String[recipes.size()]);
    }



    public boolean isFull(){

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM bizRecipe";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){
            return true;
        }else {
            return false;
        }


    }


    public boolean isFavFull(){

        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM favorite_list";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if(icount>0){
            return true;
        }else {
            return false;
        }

    }

}