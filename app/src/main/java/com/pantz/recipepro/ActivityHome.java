package com.pantz.recipepro;

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


public class ActivityHome extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        final Database sqllite =  new Database(this);

        /**
         * For nav buttons connection to each fragment
         * @author pantz
         *
         */

        theNavFunction();

        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            //SharedPreferences pref = getPreferences(MODE_PRIVATE);
            //boolean first_time = pref.getBoolean("first_time", true);
            boolean first_time = true;

            if (first_time == true) {
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
                        .url("https://tasty.p.rapidapi.com/recipes/detail?id=5586")//First 10 recipes
                        .get()
                        .addHeader("x-rapidapi-key", "10d54edbcbmsh905993d8c85037dp1ebfa7jsn234b1f93ab32")
                        .addHeader("x-rapidapi-host", "tasty.p.rapidapi.com")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();

                    SQLiteDatabase db = sqllite.getWritableDatabase();

                    JSONObject reader = new JSONObject(data);
                    JSONArray results = reader.getJSONArray("results");


                    for (int i = 0; i < results.length(); i++) {
                        JSONObject recipe = results.getJSONObject(i);

                        int id = recipe.getInt("id");
                        String recipe_title = recipe.getString("name");
                        int calories = 0;

                        try {
                            JSONObject nutrition = recipe.getJSONObject("nutrition");
                            calories = nutrition.getInt("calories");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        String sql = "INSERT OR IGNORE INTO " + Database.RECIPE_TABLE_NAME +
                                " (_id, recipe_title, recipe_category, basic_element, elements, " +
                                "exec, calories, special_d, date_added, exec_time, dif_rate) VALUES (" +
                                id + ", " + DatabaseUtils.sqlEscapeString(recipe_title) + ", '', '', '', '', " + calories + ", '', DateTime('now'), 0, 0)";

                        db.execSQL(sql);
                    }

                    System.out.println(response);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    System.out.println("Oh noooo");
                }

                //SharedPreferences.Editor editor = pref.edit();
                //editor.putBoolean("first_time", false);
            }

            // show the recipes

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
