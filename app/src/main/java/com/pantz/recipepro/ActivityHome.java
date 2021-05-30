package com.pantz.recipepro;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class ActivityHome extends AppCompatActivity {

    static final  String path = "/data/data/com.pantz.recipepro/databases/";
    static final  String DB_NAME="Recipe";
    static final  String Query = "Select * from bizRecipe";
    private String title;


    @SuppressLint("SimpleDateFormat")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        title = getIntent().getStringExtra("TEXT");


        final String TAG = "MainActivity";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final Database sqllite =  new Database(this);

        /**
         * For nav buttons connection for each fragment
         * @author pantz
         *
         */

        theNavFunction();

    }


    public View getIdOfBox(){

       View id = findViewById(R.id.searchView);
       return id;

    }

    /**
     * For each nav button <- fragment
     */
    private void theNavFunction(){


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,  R.id.fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


}