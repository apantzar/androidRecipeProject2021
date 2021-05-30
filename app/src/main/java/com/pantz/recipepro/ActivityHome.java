package com.pantz.recipepro;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;



/**
 * @author Anastasios Pantzartzis
 * In order to create Fragments (like menu) bar
 * Each button opens a specific fragment
 */

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


    /**
     * For each nav button <- fragment
     * To open each fragment like (Home, List, Account info etc)
     *
     */
    private void theNavFunction(){


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,  R.id.fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


}