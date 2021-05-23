package com.pantz.recipepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalFileParser  extends ActivityHome {

    final Database sqllitec =  new Database(this);
    //SQLiteDatabase db = sqllite.getWritableDatabase();




 /* final static Path PATH = Paths.get("src\\main\\res\\raw\\recipescsv.csv");


  File file = new File(String.valueOf(PATH));

  BufferedReader buffer;
  String line ="";
  String [] str;


    public LocalFileParser() throws IOException {

      System.out.println("I am in the class");


     // buffer = new BufferedReader(new FileReader(file));

          try {

              InputStream inputStream = getResources().openRawResource(R.raw.recipescsv);
              BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

              reader.readLine();
              reader.readLine();
              reader.readLine();
              reader.readLine();
              reader.readLine();
              reader.readLine();
              reader.readLine();
              while ((line = reader.readLine()) != null) {

                  System.out.println("I am in while the class");
                  str = line.trim().split(",");
              }

          }catch (IOException e){
              e.printStackTrace();
          }

          parseToDb();
    }


  private void parseToDb(){

      for(int i=0; i< str.length; i++){
        System.out.println("This is the array of file: "+str[i]);

        Database db = new Database(getApplicationContext());
        int id = Integer.parseInt(str[0]), execTime = Integer.parseInt(str[9]), difRate= Integer.parseInt(str[10]);
        double calories = Double.parseDouble(str[6]);
        db.writeJSONtoTheDB(id,str[1],str[2],str[3],str[4],str[5],calories,str[7],null,execTime,difRate);

      }

    }

  */




        Context context;
        String fileName;
        List<String[]> rows = new ArrayList<>();
        HashMap<Integer, String> values = new HashMap<>();

    public LocalFileParser(Context context, String fileName) {

        this.context = context;
        this.fileName = fileName;

    }


    public HashMap<Integer, String> readJSON() throws IOException, JSONException {
        String jsonFile = " ";
        int keyCounter = 0;
        InputStream is = context.getAssets().open(fileName);
      //  InputStreamReader isr = new InputStreamReader(is);

        int size = is.available();
        byte[] bufferData = new byte[size];
        is.read(bufferData);
        is.close();
        jsonFile = new String(bufferData, StandardCharsets.UTF_8);

        String id="";
        String recipeCategory="";
        String recipeTitle="";
        String dateAdded="";
        String elements="";
        String exec="";
        String basicElement="";
        String calories="";
        String difRate="";
        String execTime="";
        String specialD="";


        JSONObject reader = new JSONObject(jsonFile);
        JSONArray recipeResults = reader.getJSONArray("recipe2");

        for(int i = 0; i<recipeResults.length(); i++){
            JSONObject innerArray = recipeResults.getJSONObject(i);


            try {
                id = innerArray.getString("_id");
                System.out.println("===================id of our JSON: "+ id+"===============");
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
               // values.put(keyCounter++, "#");

            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("==========IM IN LOCALFILE READ JSON EXCEPTION==============");
            }





/*
            try {
                System.out.println("I am in try LFP");
                sqllitec.writeJSONtoTheDB(Integer.parseInt(id),recipeTitle,recipeCategory,"belement",elements,exec,
                        580,"",null,20,2);
            }catch (Exception e){
                System.out.println("I am in exception LFP");

                e.printStackTrace();
            }

 */





        }
        return values;

        //-----------------------for JSON-----------------


        //---------------------for csv----------------------
       // BufferedReader br = new BufferedReader(isr);
       // String line;
        //String csvSplitBy = ",";

      //  br.readLine();

      //  while ((line = br.readLine()) != null) {
      //      String[] row = line.split(csvSplitBy);
      //      rows.add(row);
      //  }
     //   return rows;
      //--------------------------------------------------
    }


   // public List<String> getValues() {
  //      return values;
  //  }
}


