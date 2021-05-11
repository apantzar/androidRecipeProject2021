package com.pantz.recipepro;

import android.annotation.SuppressLint;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;

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
import java.util.Iterator;


public class ActivityHome extends AppCompatActivity {

    private static int tagKey = 0;

    public String elementsIn = "ssss";



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final Database sqllite =  new Database(this);

        /**
         * For nav buttons connection for each fragment
         * @author pantz
         *
         */

        theNavFunction();

        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            //SharedPreferences pref = getPreferences(MODE_PRIVATE);
            //boolean first_time = pref.getBoolean("first_time", true);
            boolean first_time = true;

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
                    .url("https://tasty.p.rapidapi.com/recipes/list?from=0&size=1000")//First 10 recipes
                    .get()
                    .addHeader("x-rapidapi-key", "26a22ee7a7msh03d13c0b22a034cp1317e8jsn479f9b5ec601")
                    .addHeader("x-rapidapi-host", "tasty.p.rapidapi.com")
                    .build();


            /**
             * For JSON data
             */

            try {
                Response response = client.newCall(request).execute();
                String data = response.body().string();

                SQLiteDatabase db = sqllite.getWritableDatabase();

                JSONObject reader = new JSONObject(data);
                JSONArray results = reader.getJSONArray("results");



                //tESTING (pRINT)


                int counterOfRecipes = 0;


                for(int i=0; i<results.length(); i++){

                    JSONObject inner = results.getJSONObject(i);



                    //For keys



                   int id = inner.getInt("id");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!This is the id of:  "+id);

                    try{
                        JSONArray recipesArray = inner.getJSONArray("recipes");


                        /*for(int j=0; j<recipesArray.length(); j++){
                            if(recipesArray.get(j).equals("tags")){
                                tagKey = j;
                            }
                        }*/




                        JSONObject recipeObj = recipesArray.getJSONObject(0);
                      //  JSONObject tagObj = recipesArray.getJSONObject(tagKey);


                        String recipeName = recipeObj.getString("name");
                        //String catName = tagObj.getString("display_name");


                        //System.out.println("The tag is: "+catName);
                        System.out.println("This will be the name:  "+recipeName);
                        counterOfRecipes++;

                        System.out.println("Recipes Number: "+counterOfRecipes);
                    }catch (Exception e){


                    }






                   // String recipeTitle = inner.getString("")

                }







            } catch (IOException | JSONException e) {
                e.printStackTrace();
                System.out.println("Oh noooo");
            }

        });

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
