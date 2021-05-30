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


                                        JSONArray recipesArray = inner.getJSONArray("recipes");
                                        JSONObject innerParser = recipesArray.getJSONObject(0);
                                        JSONArray sectionsAr = innerParser.getJSONArray("sections");
                                        JSONObject innerSections = sectionsAr.getJSONObject(0);
                                        JSONArray compArray = innerSections.getJSONArray("components");
                                        String recipeName = innerParser.getString("name");

                                        String[] rawTextArray = new String[compArray.length()];


                                        JSONArray instructionsArray = innerParser.getJSONArray("instructions");


                                        /**
                                         * This is for elements
                                         */

                                        for (int j = 0; j < compArray.length(); j++) {

                                            JSONObject innerObj = compArray.getJSONObject(j);
                                            for (Iterator it = innerObj.keys(); it.hasNext(); ) {
                                                String key = (String) it.next();
                                             //   System.out.println(key + "!!:" + innerObj.get(key));

                                                if (key.equals("raw_text")) {

                                                    rawTextArray[j] = (String) innerObj.get(key);
                                                    elementsStr += (String) innerObj.get(key) + "| ";

                                                }


                                            }

                                        }


                                  //      System.out.println("Third print of raw text: " + elementsStr);


                                        /**
                                         * This is for instructions:
                                         */


                                        for (int in = 0; in < instructionsArray.length(); in++) {

                                            JSONObject insObj = instructionsArray.getJSONObject(in);
                                            for (Iterator it = insObj.keys(); it.hasNext(); ) {
                                                String key = (String) it.next();
                                              //  System.out.println(key + "!!:" + insObj.get(key));

                                                if (key.equals("display_text")) {

                                                    execStr += (String) insObj.get(key) + "| ";

                                                }

                                            }

                                        }


                                        sqllite.writeJSONtoTheDB(id, recipeName, "cat", "belement", elementsStr,
                                                execStr, 225, "spd", null, 25, 2, null);



                                        counterOfRecipes++;


                                    } catch (Exception e) {

                                        e.printStackTrace();

                                    }

                                }


                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
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
                    e.printStackTrace();
                }


            } catch (IOException | JSONException e) {
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