package com.pantz.recipepro;



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



}


