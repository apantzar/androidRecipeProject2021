package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class activity_details extends AppCompatActivity {

    private String textTitle, textExec, cat, elements, exectime, calories, difrate, path;
    private Bitmap bitmap;
    ImageView imageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FourthFragment fg = new FourthFragment();
       /* String text = fg.getDisplayText();

        System.out.println("Text test "+text);*/

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

    }

}