package com.pantz.recipepro;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


    public LocalFileParser(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;

    }


    public List<String[]> readCSV() throws IOException {
        InputStream is = context.getAssets().open(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String csvSplitBy = ",";

        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] row = line.split(csvSplitBy);
            rows.add(row);
        }
        return rows;
    }

}


