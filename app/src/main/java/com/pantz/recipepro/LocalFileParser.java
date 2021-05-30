package com.pantz.recipepro;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





/**
 * @author Anna Tzanakapoulou
 * Class to read the JSON file
 * Returns the values in HashMap
 * in order to be in database
 *
 * Collaboration with SplashScreen.java
 *
 * @return values
 * @throws IOException ->for file problem
 * @throws JSONException -> for JSON file problems
 */

public class LocalFileParser  extends ActivityHome {

    private  String  fileName, jsonFile = " ", id="", recipeCategory="",recipeTitle="",dateAdded="",elements="",exec="",basicElement="",calories="",difRate="",execTime="",specialD="",imgPath="";
    private int keyCounter = 0;
    //final Database sqllitec =  new Database(this);

    Context context;
    List<String[]> rows = new ArrayList<>();
    private HashMap<Integer, String> values = new HashMap<>();

    public LocalFileParser(Context context, String fileName) {

        this.context = context;
        this.fileName = fileName;

    }




    public HashMap<Integer, String> readJSON() throws IOException, JSONException {


        //read
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] bufferData = new byte[size];
        is.read(bufferData);
        is.close();
        jsonFile = new String(bufferData, StandardCharsets.UTF_8);




        JSONObject reader = new JSONObject(jsonFile);
        JSONArray recipeResults = reader.getJSONArray("recipe2");

        for(int i = 0; i<recipeResults.length(); i++){
            JSONObject innerArray = recipeResults.getJSONObject(i);


            try {


                //Read from JSON
                id = innerArray.getString("_id");
                recipeCategory = innerArray.getString("recipe_category");
                recipeTitle = innerArray.getString("recipe_title");
                dateAdded = innerArray.getString("date_added");
                elements = innerArray.getString("_elements");
                exec = innerArray.getString("exec");
                basicElement =innerArray.getString("basic_element");
                calories =innerArray.getString("calories");
                difRate =innerArray.getString("dif_rate");
                execTime =innerArray.getString("exec_time");
                specialD =innerArray.getString("special_d");
                imgPath=innerArray.getString("img");


                //Put data to HashMap
                values.put(keyCounter++, id);
                values.put(keyCounter++, recipeTitle);
                values.put(keyCounter++, recipeCategory);
                values.put(keyCounter++, basicElement);
                values.put(keyCounter++, elements);
                values.put(keyCounter++, exec);
                values.put(keyCounter++, calories);
                values.put(keyCounter++, specialD);
                values.put(keyCounter++, dateAdded);
                values.put(keyCounter++, execTime);
                values.put(keyCounter++, difRate);
                values.put(keyCounter++, imgPath);



            } catch (JSONException e) {
                e.printStackTrace();

            }




        }

        //returns the Map
        return values;


    }


}


