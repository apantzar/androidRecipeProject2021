package com.pantz.recipepro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import static android.content.ContentValues.TAG;

public class SplashScreen extends AppCompatActivity {

    final Database sqllite =  new Database(this);
    Database db = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        Boolean tableStatus = db.isFull();


        System.out.println("Is the table of db full? "+tableStatus);


        if(!tableStatus) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    /**
                     * The API runs in different Thread now
                     * @author Anastasios Pantzartzis
                     * The API runs in different thread in order to
                     * avoid UI Freezing
                     */
                    synchronized (this) {
                        try {

                            boolean first_time = true;

                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


                            StrictMode.setThreadPolicy(policy);


                            /**
                             * API: TASTY
                             * This API's code for requests
                             * The result of the call will be stored in database
                             * @author Anastasios Pantzartzis
                             */

                            OkHttpClient client = new OkHttpClient();

                            Request request = new Request.Builder()
                                    .url("https://tasty.p.rapidapi.com/recipes/list?from=0&size=1000")
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


                                for (int i = 0; i < results.length(); i++) {

                                    JSONObject inner = results.getJSONObject(i);


                                    //For keys


                                    int id = inner.getInt("id");
                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!This is the id of:  " + id);

                                    try {
                                        String elementsStr = "";
                                        String execStr = "";
                                        int calories = 225;
                                        // int totalTime = 20;


                                        JSONArray recipesArray = inner.getJSONArray("recipes");
                                        // JSONObject totalTimeObj = inner.getJSONObject("total_time_minutes");
                                        JSONObject innerParser = recipesArray.getJSONObject(0);
                                        JSONArray sectionsAr = innerParser.getJSONArray("sections");
                                        JSONObject innerSections = sectionsAr.getJSONObject(0);
                                        JSONArray compArray = innerSections.getJSONArray("components");
                                        //JSONObject nutritionObj = inner.getJSONObject("nutrition");
                                        //calories = nutritionObj.getInt("calories");
                                        String recipeName = innerParser.getString("name");

                                        //totalTime = totalTimeObj.getInt("total_time_minutes");

                                        // if(totalTime<=0){

                                        //    totalTime = 20;

                                        //}

                                        // if(calories<=0 ){
                                        //      calories= 225;
                                        //   }


                                        String[] rawTextArray = new String[compArray.length()];


                                        JSONArray instructionsArray = innerParser.getJSONArray("instructions");


                                        /**
                                         * This is for elements
                                         */

                                        for (int j = 0; j < compArray.length(); j++) {
                                            //elementsStr="";
                                            //String compValue = compArray.optString("raw_text");
                                            // System.out.println("The raw text is:  "+compValue);

                                            JSONObject innerObj = compArray.getJSONObject(j);
                                            for (Iterator it = innerObj.keys(); it.hasNext(); ) {
                                                String key = (String) it.next();
                                                System.out.println(key + "!!:" + innerObj.get(key));

                                                if (key.equals("raw_text")) {

                                                    rawTextArray[j] = (String) innerObj.get(key);
                                                    elementsStr += (String) innerObj.get(key) + "| ";

                                                }


                                                //System.out.println("First print of raw text: "+elementsStr);
                                            }


                                            // System.out.println("Second print of raw text: "+elementsStr);




                          /*  for(int k=0;k<rawTextArray.length; k++){



                               elementsStr += rawTextArray[k];



                            }*/
                                        }


                                        System.out.println("Third print of raw text: " + elementsStr);


                                        /**
                                         * This is for instructions:
                                         */


                                        for (int in = 0; in < instructionsArray.length(); in++) {

                                            JSONObject insObj = instructionsArray.getJSONObject(in);
                                            for (Iterator it = insObj.keys(); it.hasNext(); ) {
                                                String key = (String) it.next();
                                                System.out.println(key + "!!:" + insObj.get(key));

                                                if (key.equals("display_text")) {


                                                    System.out.println("I am here baby");

                                                    //rawTextArray[in] = (String) insObj.get(key);
                                                    execStr += (String) insObj.get(key) + "| ";

                                                }


                                                //  System.out.println("First print of raw text: "+elementsStr);
                                            }

                                        }


                                        sqllite.writeJSONtoTheDB(id, recipeName, "cat", "belement", elementsStr,
                                                execStr, 225, "spd", null, 25, 2, null);


                                        //Testing for results
                                        System.out.println("Second print of exec: " + execStr);

                                        System.out.println("Calories ==> " + calories);

                                        // System.out.println("Total time ==> "+totalTime);


                                        for (int k = 0; k < rawTextArray.length; k++) {


                                            //String elementsStr = rawTextArray;

                                            System.out.println("-----------------------[here]--------------------------------------");
                                            System.out.println("Raw text: " + k + " => " + rawTextArray[k]);

                                        }


                                        // JSONArray section = inner.getJSONArray("sections");
                                        //  JSONArray comp = section.getJSONArray(0);

                                        System.out.println("\n=========================================================");

                                        System.out.println(inner);

                                        System.out.println("\n\n=========================================================");


                                        counterOfRecipes++;


                                        System.out.println("Recipes Number: " + counterOfRecipes);


                                    } catch (Exception e) {

                                        e.printStackTrace();

                                    }

                                    // String recipeTitle = inner.getString("")

                                }


                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                                System.out.println("Oh noooo");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }

                    Log.i(TAG, "run: Finished");

                }





            };


            Thread thread = new Thread(runnable);
            thread.start();



            /**
             * For local file to db because API sucks :(
             * The free API has limited recipes, we are going to add
             * from JSON file extra recipes now.
             * @authors: Anna Tzanakopoulou, Anastasios Pantzartzis
             */

            HashMap<Integer, String> rows = new HashMap<>();


            LocalFileParser localFileParser = new LocalFileParser(this, "recipes.json");

            try {

                rows = localFileParser.readJSON();

                try {

                    int counter = 0;

                    for (int i = 0; i < 49; i++) {

                        sqllite.writeJSONtoTheDB(Integer.parseInt(rows.get(counter++)), rows.get(counter++), rows.get(counter++),
                                rows.get(counter++), rows.get(counter++), rows.get(counter++), Integer.parseInt(rows.get(counter++)), rows.get(counter++),
                                new SimpleDateFormat("dd/MM/yyyy").parse(rows.get(counter++)),
                                Integer.parseInt(rows.get(counter++)), Integer.parseInt(rows.get(counter++)), rows.get(counter++));


                    }


                } catch (NumberFormatException | ParseException e) {
                    System.out.println("WHY DONT YOU TAKE 9????????");
                    e.printStackTrace();
                }


            } catch (IOException | JSONException e) {

                System.out.println("!!!!!!!!!!!!!!!!I am in exception for csv!!!!!!!!!!!!!!!");
                e.printStackTrace();
            }
        }






        /**
         * @author Dimitris Peftitsis
         *
         */

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class); startActivity(i);
                finish(); } }, 1500);
    }
}