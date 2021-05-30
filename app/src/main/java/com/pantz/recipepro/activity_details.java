package com.pantz.recipepro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

public class activity_details extends AppCompatActivity {


    /**
     *
     * @author Anna Tzanakopoulou, Anastasios Pantzartzis
     *
     * This activity is about to show details about the recipes
     * Collaborates with SecondFragment, FourthFragment in order
     * to get the details of the recipe like execution, time, elements etc..
     *
     */

    private String textTitle, textExec, cat, elements, exectime, calories, difrate, path, titleToFav;
    ImageView imageView;
    String [] lovedRecipes;
    Database db;
    Boolean flagOfLove = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       // FourthFragment fg = new FourthFragment();
        ImageView favButton = findViewById(R.id.imageViewFav);

    /***
     *
     * To get the elements with this specific "key"
     *
     * For example textTitle will get the value that
     * the TEXT 'points to'
     *
     */

        try{
        textTitle=getIntent().getStringExtra("TEXT");
        textExec=getIntent().getStringExtra("EXEC");
        calories=getIntent().getStringExtra("CAL");
        difrate = getIntent().getStringExtra("DIFRATE");
        exectime = getIntent().getStringExtra("EXECTIME").toString();
        cat = getIntent().getStringExtra("CAT");
        elements = getIntent().getStringExtra("ELEMENTS");
        path = getIntent().getStringExtra("IMAGE");}catch (NullPointerException ignored){}


        /**
         * To get & set image
         * for each recipe
         */
        try{
            String uri=path;

            int imageResource = getResources().getIdentifier(uri, null, getPackageName());

            imageView= (ImageView)findViewById(R.id.imageView8);
            Drawable res = getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);
        }catch(NullPointerException ignored){}


        /**
         * @authors Anna Tzanakopoulou, Anastasios Pantzartzis
         *
         *
         * Stores the data of the favorite list into string array
         * For each element checks if the recipe title is in this array
         * in order to fill the heart or not
         *
         *
         */
        try {
            db = new Database(this);
            lovedRecipes = db.getData("SELECT * FROM favorite_list ", "fav_title");
            for(int i =0; i<lovedRecipes.length;i++){

               // System.out.println("TextTitle "+textTitle);
                if(lovedRecipes[i].trim().equals(textTitle)){

                   flagOfLove=true;


                }
            }


            /**
             * Change the heart icon
             */

            if(flagOfLove){

                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_close));


            }else {
                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_open));

            }


        }catch (NullPointerException e){

            //System.out.println("Catch Catch Catch");;
            e.printStackTrace();
        }


        /**
         * Set the text of  TextViews
         *
         * 1)Finds the TextViews ids
         * 2)Sets texts
         */

        //  System.out.println("txtTITLE "+textTitle);

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

        exec.setMovementMethod(new ScrollingMovementMethod());

        //System.out.println("From details exec "+textExec);


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


        /**
         * If the heart ImageView pressed
         * Adds title to the favorite database
         *
         * Heart symbols is close (now)
         *
         *
         */

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                favButton.setImageDrawable(ContextCompat.getDrawable(activity_details.this, R.drawable.heart_close));

                TextView textView = findViewById(R.id.txtTitle);
                titleToFav = textView.getText().toString();

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