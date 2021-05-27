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


    private static int tagKey = 0;

    private String title;
    public String elementsIn = "ssss";



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



        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(v -> {


            //getData();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    /**
                     * The API runs in different Thread now
                     * @author apantzar
                     */
                    synchronized (this){
                        try{



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
                                        String elementsStr = "";
                                        String execStr="";
                                        int calories=225;
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

                                        for(int j=0; j<compArray.length();j++){
                                            //elementsStr="";
                                            //String compValue = compArray.optString("raw_text");
                                            // System.out.println("The raw text is:  "+compValue);

                                            JSONObject innerObj = compArray.getJSONObject(j);
                                            for(Iterator it = innerObj.keys(); it.hasNext(); ) {
                                                String key = (String)it.next();
                                                System.out.println(key + "!!:" + innerObj.get(key));

                                                if(key.equals("raw_text")){

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


                                        System.out.println("Third print of raw text: "+elementsStr);


                                        /**
                                         * This is for instructions:
                                         */



                                        for(int in=0; in<instructionsArray.length(); in++){

                                            JSONObject insObj = instructionsArray.getJSONObject(in);
                                            for(Iterator it = insObj.keys(); it.hasNext(); ) {
                                                String key = (String)it.next();
                                                System.out.println(key + "!!:" + insObj.get(key));

                                                if(key.equals("display_text")){


                                                    System.out.println("I am here baby");

                                                    //rawTextArray[in] = (String) insObj.get(key);
                                                    execStr += (String) insObj.get(key) + "| ";

                                                }


                                                //  System.out.println("First print of raw text: "+elementsStr);
                                            }

                                        }



                                       sqllite.writeJSONtoTheDB(id, recipeName, "cat", "belement", elementsStr,
                                                execStr, 225, "spd", null, 25,2, null);


                                        //Testing for results
                                        System.out.println("Second print of exec: "+execStr);

                                        System.out.println("Calories ==> "+calories);

                                        // System.out.println("Total time ==> "+totalTime);








                                        for(int k=0;k<rawTextArray.length; k++){


                                            //String elementsStr = rawTextArray;

                                            System.out.println("-----------------------[here]--------------------------------------");
                                            System.out.println("Raw text: "+k+ " => "+rawTextArray[k]);

                                        }



                                        // JSONArray section = inner.getJSONArray("sections");
                                        //  JSONArray comp = section.getJSONArray(0);

                                        System.out.println("\n=========================================================");

                                        System.out.println(inner);

                                        System.out.println("\n\n=========================================================");


                                        counterOfRecipes++;




                                        System.out.println("Recipes Number: "+counterOfRecipes);



                                    }catch (Exception e){

                                        e.printStackTrace();

                                    }

                                    // String recipeTitle = inner.getString("")

                                }


                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                                System.out.println("Oh noooo");
                            }

                        }catch (Exception e){
                            e.printStackTrace();

                        }

                    }

                    Log.i(TAG, "run: Finished");

                }


                /**
                 * for local file to db because API sucks
                 * @authors: atzanako ,apantzar
                 */



                /*LocalFileParser fileParser;

                {
                    try {

                        System.out.println("I am in try (HomeActivity)");
                        fileParser = new LocalFileParser();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/


          /*      LocalFileParser lf;

                {
                    try {
                        lf = new LocalFileParser();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }




                List<String[]> rows = new ArrayList<>();
        CSVReader csvReader = new CSVReader(RatingActivity.this, "movies.csv");
        try {
            rows = csvReader.readCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < rows.size(); i++) {
            Log.d(Constants.TAG, String.format("row %s: %s, %s", i, rows.get(i)[0], rows.get(i)[1]));
        }
    }



  */
            };



           Thread thread = new Thread(runnable);
           thread.start();


            HashMap<Integer, String> rows = new HashMap<>();
            HashMap<Integer, String> imgRows = new HashMap<>();




            LocalFileParser localFileParser = new LocalFileParser(this, "recipes.json");

            try{

                rows = localFileParser.readJSON();
               // imgRows = localFileParser.getImgValues();
                //System.out.println("Element: "+ rows);
                System.out.println("IM IN TH ROWS HASHMAP=============================");
               /* for(int i = 0; i<imgRows.size(); i++){
                   // if(rows.get(i)=="#"){
                    //    i++;
                   // }else{

                    System.out.println(imgRows.get(i));
                      //  System.out.println(rows.get(i));
                  //  }
                }*/





                try{

                    int counter = 0;

                    for(int i =0; i<49;i++){

                            System.out.println("Caltest "+rows.get(6));
                            System.out.println("CATtEST "+rows.get(2));

                            sqllite.writeJSONtoTheDB(Integer.parseInt(rows.get(counter++)),rows.get(counter++), rows.get(counter++),
                                    rows.get(counter++),rows.get(counter++), rows.get(counter++), Integer.parseInt(rows.get(counter++)), rows.get(counter++),
                                    new SimpleDateFormat("dd/MM/yyyy").parse(rows.get(counter++)),
                                    Integer.parseInt(rows.get(counter++)), Integer.parseInt(rows.get(counter++)), rows.get(counter++));



                    }


                }catch (NumberFormatException | ParseException e){
                    System.out.println("WHY DONT YOU TAKE 9????????");
                    e.printStackTrace();
                }


            }catch (IOException | JSONException e){

                System.out.println("!!!!!!!!!!!!!!!!I am in exception for csv!!!!!!!!!!!!!!!");
                e.printStackTrace();
            }



/*
            Database theDatabase = new Database(this);

            for(int i=0; i< rows.size(); i++){
                System.out.println("Inside the loop");
                System.out.println(rows.get(i));
                theDatabase.writeJSONtoTheDB(Integer.parseInt(rows.get(0)),rows.get(1) , "cat", "noth"
                ,"elelel","sddd",50,"sddd",null,20,2);

            }

*/





           // for(int i =0; i<rows.size();i++){

           // }





        //    InsideCSVParser insider = new InsideCSVParser();
        //    insider.giveTheRecipesNow();



        });




    }


   /* public void getData(){
        try(

                Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(path, "", "");
            Statement statement = conn.createStatement();
            ResultSet result  = statement.executeQuery(Query);

        ){

            while(result.next()){

                System.out.println("ID: "+result.getInt("_id"));
                System.out.println("BASIC ELEMENT"+result.getString("basic_element"));

            }


        }catch (SQLException | ClassNotFoundException e){


            System.out.println("NEW EXCEPRION AGAINNNNNNNNNNNNNNN");

            e.printStackTrace();
        }


    }*/



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