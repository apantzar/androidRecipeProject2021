package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SQL conn

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("dbRecipe.bd", MODE_PRIVATE, null);


        //test
    }
}