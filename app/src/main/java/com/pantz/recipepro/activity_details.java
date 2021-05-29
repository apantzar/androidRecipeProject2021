package com.pantz.recipepro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class activity_details extends AppCompatActivity {

    private String textTitle, textExec, cat, elements, exectime, calories, difrate, path, titleToFav;
    private Bitmap bitmap;
    ImageView imageView;
    String [] lovedRecipes;
    Database db;
    Boolean flagOfLove = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FourthFragment fg = new FourthFragment();
       /* String text = fg.getDisplayText();



        System.out.println("Text test "+text);*/

        ImageView favButton = findViewById(R.id.imageViewFav);



        try{
        textTitle=getIntent().getStringExtra("TEXT");
        textExec=getIntent().getStringExtra("EXEC");
        calories=getIntent().getStringExtra("CAL");
        difrate = getIntent().getStringExtra("DIFRATE");
        exectime = getIntent().getStringExtra("EXECTIME").toString();
        cat = getIntent().getStringExtra("CAT");
        elements = getIntent().getStringExtra("ELEMENTS");
        path = getIntent().getStringExtra("IMAGE");}catch (NullPointerException ignored){}

        System.out.println("PATHHHHHHHHH "+path);


        try{
            String uri=path;

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            imageView= (ImageView)findViewById(R.id.imageView8);
            Drawable res = getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);
        }catch(NullPointerException ignored){}




        try {
            db = new Database(this);
            lovedRecipes = db.getData("SELECT * FROM favorite_list ", "fav_title");

            System.out.println("<-------------------------------------------------->");
            System.out.println("Array : ");
            for(int i =0; i<lovedRecipes.length;i++){

                System.out.println("TextTitle "+textTitle);
                if(lovedRecipes[i].trim().equals(textTitle)){

                   flagOfLove=true;


                }
            }


            if(flagOfLove){

                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_close));


            }else {
                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_open));

            }

            System.out.println("<-------------------------------------------------->");

        }catch (NullPointerException e){

            System.out.println("Catch Catch Catch");;
            e.printStackTrace();
        }



/*
        File imgFile = new  File(path);


        if(imgFile.exists()){

            bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        }else {
            System.out.println("Not found");
        }*/



        System.out.println("txtTITLE "+textTitle);

        TextView view  = findViewById(R.id.txtTitle);
        view.setText(textTitle);

        TextView exec = findViewById(R.id.textExec);
        exec.setText("Elements:\n"+ elements+ "\n\n" +"Execution:\n"+textExec );

        TextView cal = findViewById(R.id.textView5);
        cal.setText(calories);

        TextView dif = findViewById(R.id.textView3);
        dif.setText(difrate);

        TextView sExecTime = findViewById(R.id.textView6);
        sExecTime.setText(exectime);

        TextView catTxt = findViewById(R.id.textView10);
        catTxt.setText(cat);

        ImageView imageView = findViewById(R.id.imageView8);
       // imageView.setImageBitmap(bitmap);




        exec.setMovementMethod(new ScrollingMovementMethod());

        System.out.println("From details exec "+textExec);


        /**
         * To close the activity
         */
        ImageView imageView1 = findViewById(R.id.imageVBack);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //favButton.setBackgroundResource(R.drawable.heart_close);
                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_close));


                TextView textView = findViewById(R.id.txtTitle);
                titleToFav = textView.getText().toString();


                System.out.println("FAV FAV FAV FAV"+titleToFav);
                Database favDb = new Database(getApplicationContext());
                favDb.addFavToDatabaseNow(titleToFav);




            }
        });

    }


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull String name, @NonNull @NotNull Context context, @NonNull @NotNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}