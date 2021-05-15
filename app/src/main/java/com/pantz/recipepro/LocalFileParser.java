package com.pantz.recipepro;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LocalFileParser extends ActivityHome {



  final static Path PATH = Paths.get("src\\main\\res\\raw\\recipescsv.csv");


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
}


