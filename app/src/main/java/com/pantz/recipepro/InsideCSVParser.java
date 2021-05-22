package com.pantz.recipepro;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;

public class InsideCSVParser extends MainActivity{


    public void giveTheRecipesNow(){

        Database sendToDatabase = new Database(this);
        sendToDatabase.writeJSONtoTheDB(8505,"Anna brasti","Human","Anna","anna","fbywbfuybwfuybwefuywebfyuew",850,"nothing", null, 50,2);


    }




}
