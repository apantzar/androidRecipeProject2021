package com.pantz.recipepro;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


//@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalFileParser extends ActivityHome {



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
    List<String> values = new ArrayList<>();

    public LocalFileParser(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;

    }


    public List<String> readJSON() throws IOException, JSONException {
        String jsonFile = " ";
        InputStream is = context.getAssets().open(fileName);
      //  InputStreamReader isr = new InputStreamReader(is);

        int size = is.available();
        byte[] bufferData = new byte[size];
        is.read(bufferData);
        is.close();
        jsonFile = new String(bufferData, StandardCharsets.UTF_8);


        JSONObject reader = new JSONObject(jsonFile);
        JSONArray recipeResults = reader.getJSONArray("recipe2");

        for(int i = 0; i<recipeResults.length(); i++){
            JSONObject innerArray = recipeResults.getJSONObject(i);

            String id = innerArray.getString("_id");
            values.add(id);

            System.out.println("===================id of our JSON: "+ id+"===============");

            String recipeCategory = innerArray.getString("recipe_category");
            values.add(recipeCategory);
            String recipeTitle = innerArray.getString("recipe_title");
            values.add(recipeTitle);
            String dateAdded = innerArray.getString("date_added");
            values.add(dateAdded);
            String elements = innerArray.getString("_elements");
            values.add(elements);
            String exec = innerArray.getString("exec");
            values.add(exec);

            try {
                String basicElement =innerArray.getString("basic_element");
                values.add(basicElement);
                String calories =innerArray.getString("calories");
                values.add(calories);
                String difRate =innerArray.getString("dif_rate");
                values.add(difRate);
                String execTime =innerArray.getString("exec_time");
                values.add(execTime);
                String specialD =innerArray.getString("special_d");
                values.add(specialD);

            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("==========IM IN LOCALFILE READ JSON EXCEPTION==============");
            }

            values.add("#");


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

}


