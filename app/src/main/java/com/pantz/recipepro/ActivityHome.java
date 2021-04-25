package com.pantz.recipepro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class ActivityHome extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,  R.id.fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        /**
         * For nav buttons connection to each fragment
         * @author pantz
         *
         */

        /*

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.firstFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){

                    case R.id.firstFragment:
                        selectedFragment = new FirstFragment();

                        break;


                    case R.id.secondFragment:
                       selectedFragment = new SecondFragment();
                       break;


                    case R.id.thirdFragment:
                       selectedFragment = new ThirdFragment();
                       break;



                    case R.id.fourthFragment:
                       selectedFragment = new FourthFragment();
                       break;



                }
               getSupportFragmentManager().beginTransaction().replace(R.id.secondFragment, selectedFragment).commit();
                return true;
            }
        });*/







        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(v -> {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);


            /**
             * This API's code for requests
             * The result of the call will be displayed (console) for now.
             *
             * @author pantz
             */

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://tasty.p.rapidapi.com/tags/list")
                    .get()
                    .addHeader("x-rapidapi-key", "26a22ee7a7msh03d13c0b22a034cp1317e8jsn479f9b5ec601")
                    .addHeader("x-rapidapi-host", "tasty.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();

                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Oh noooo");
            }





        });




    }


}
