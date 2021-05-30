package com.pantz.recipepro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment{


    private String displayText;
    private String exec;
    private String cal;
    private String difRate;
    private String execTime;
    private String elements;
    private String cat;
    private String path;
    private  Database qdb;
    private int r1,r2,r3,r4,r5,r6,r7,r8;
    Database db;
    private String[] array , imgArray, image, title;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


        Random random = new Random();
        int lowLimit = 1;
        int highLimit =49 ;


        /**
         * :/ I know....
         *
         *
         * In order to be unique each (r)
         */
        while((r4==r3 || r4==r2 || r4==r1 ) || (r3==r2 || r3==r1) || (r2==r1)   ||

              (r5==r4 || r5==r3 || r5==r2 ) || (r5==r1 || r6==r5) || (r6==r4) ||

              (r6==r3 || r6==r2 || r6==r1 ) || (r7==r6 || r7==r5) || (r7==r4) ||

              (r7==r3 || r7==r2 || r7==r1 ) || (r8==r7 || r8==r6) || (r8==r5) ||

              (r8==r4 || r8==r3 || r8==r2 ) || (r8==r1) || (r1==9 || r2==9 || r3==9 || r4==9 || r5==9||r6==9||r7==9|| r8==9) ){

          r1 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r2 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r3 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r4 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r5 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r6 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r7 = random.nextInt(highLimit - lowLimit)+lowLimit;
          r8 = random.nextInt(highLimit - lowLimit)+lowLimit;


      }




        db = new Database(getContext());
        array = db.getData("SELECT * from bizRecipe where _id ="+ r1 +" OR  _id="+ r2 +" OR _id="+ r3+
                " OR  _id="+ r4 +" OR _id="+ r5
                +" OR  _id="+ r6 +" OR _id="+ r7+" OR  _id="+ r8 , "recipe_title");


        imgArray = db.getData("SELECT path from bizRecipe where _id ="+ r1 +" OR  _id="+ r2 +" OR _id="+ r3+
                " OR  _id="+ r4 +" OR _id="+ r5
                +" OR  _id="+ r6 +" OR _id="+ r7+" OR  _id="+ r8 , "path");

        System.out.println("r1, r2, r3, r4, r5, r6, r7, r8 "+ r1+" "+r2+" "+r3+" "+ r4+" "+r5+" "+r6 +" "+r7+" "+r8);


        for (String s : array) {
            System.out.println("This is Array: " + s);

        }

        for (String s : imgArray ) {
            System.out.println("This is Array: " + s);

        }

    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first, container, false);


            TextView view0 = view.findViewById(R.id.recipe1txt);
            view0.setText(array[0]);


            TextView view1 = view.findViewById(R.id.recipe2txt);
            view1.setText(array[1]);


            TextView view2 = view.findViewById(R.id.recipe3txt);
            view2.setText(array[2]);

            TextView view3 = view.findViewById(R.id.recipe4txt);
            view3.setText(array[3]);

            TextView view4 = view.findViewById(R.id.recipe5txt);
            view4.setText(array[4]);


            TextView view5 = view.findViewById(R.id.recipe6txt);
            view5.setText(array[5]);


            TextView view6 = view.findViewById(R.id.recipe7txt);
            view6.setText(array[6]);

            TextView view7 = view.findViewById(R.id.recipe8txt);
            view7.setText(array[7]);


            ImageView img1 = view.findViewById(R.id.imgView0);
            ImageView img2 = view.findViewById(R.id.imgView1);
            ImageView img3 = view.findViewById(R.id.imgView2);
            ImageView img4 = view.findViewById(R.id.imgView3);
            ImageView img5 = view.findViewById(R.id.imgView4);
            ImageView img6 = view.findViewById(R.id.imgView);
            ImageView img7 = view.findViewById(R.id.imgView6);
            ImageView img8 = view.findViewById(R.id.imgView7);

            initTheImages(imgArray[0], img1);
            initTheImages(imgArray[1], img2);
            initTheImages(imgArray[2], img3);
            initTheImages(imgArray[3], img4);
            initTheImages(imgArray[4], img5);
            initTheImages(imgArray[5], img6);
            initTheImages(imgArray[6], img7);
            initTheImages(imgArray[7], img8);


            CardView cardView1 = view.findViewById(R.id.card1);
            CardView cardView2 = view.findViewById(R.id.card2);
            CardView cardView3 = view.findViewById(R.id.card3);
            CardView cardView4 = view.findViewById(R.id.card4);
            CardView cardView5 = view.findViewById(R.id.card5);
            CardView cardView6 = view.findViewById(R.id.card6);
            CardView cardView7 = view.findViewById(R.id.card7);
            CardView cardView8 = view.findViewById(R.id.card8);


            titleValueByClickCard(view, cardView1, cardView2, cardView3, cardView4,
                    cardView5, cardView6, cardView7, cardView8);




        return view;
    }



    public void titleValueByClickCard(View view, CardView cardView1,  CardView cardView2,  CardView cardView3,  CardView cardView4,
                                      CardView cardView5,  CardView cardView6,  CardView cardView7, CardView cardView8 ){


        TextView view1T =view.findViewById(R.id.recipe1txt);
        String t1 = view1T.getText().toString();

        TextView view2T =view.findViewById(R.id.recipe2txt);
        String t2 = view2T.getText().toString();

        TextView view3T =view.findViewById(R.id.recipe3txt);
        String t3 = view3T.getText().toString();

        TextView view4T =view.findViewById(R.id.recipe4txt);
        String t4 = view4T.getText().toString();

        TextView view5T =view.findViewById(R.id.recipe5txt);
        String t5 = view5T.getText().toString();

        TextView view6T =view.findViewById(R.id.recipe6txt);
        String t6 = view6T.getText().toString();

        TextView view7T =view.findViewById(R.id.recipe7txt);
        String t7 = view7T.getText().toString();

        TextView view8T =view.findViewById(R.id.recipe8txt);
        String t8 = view8T.getText().toString();



        openCardWithClickNow(cardView1, t1);
        openCardWithClickNow(cardView2, t2);
        openCardWithClickNow(cardView3, t3);
        openCardWithClickNow(cardView4, t4);
        openCardWithClickNow(cardView5, t5);
        openCardWithClickNow(cardView6, t6);
        openCardWithClickNow(cardView7, t7);
        openCardWithClickNow(cardView8, t8);





    }





    private void openCardWithClickNow(CardView cardView, String displayText){
        cardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

                qdb = new Database(getContext());
                exec = qdb.getExec("SELECT exec FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec" );
                cal = qdb.getExec("SELECT calories FROM bizRecipe where recipe_title like '%"+displayText+"%'","calories" );
                difRate = qdb.getExec("SELECT dif_rate  FROM bizRecipe where recipe_title like '%"+displayText+"%'","dif_rate" ).toString();
                execTime = qdb.getExec("SELECT exec_time  FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec_time" ).toString();
                elements = qdb.getExec("SELECT _elements FROM bizRecipe where recipe_title like '%"+displayText+"%'","_elements" );
                cat = qdb.getExec("SELECT recipe_category FROM bizRecipe where recipe_title like '%"+displayText+"%'","recipe_category" );
                path = qdb.getExec("SELECT path FROM bizRecipe where recipe_title like '%"+displayText+"%'","path" );


                Intent intent = new Intent(FirstFragment.this.getActivity(), activity_details.class);
                intent.putExtra("TEXT", displayText);
                intent.putExtra("EXEC", exec);
                intent.putExtra("CAL", cal);
                intent.putExtra("DIFRATE", difRate);
                intent.putExtra("EXECTIME",execTime);
                intent.putExtra("ELEMENTS",elements);
                intent.putExtra("CAT",cat);
                intent.putExtra("IMAGE", path);
                startActivity(intent);
            }
        });
    }


    private void initTheImages(String uri, ImageView imageView){


        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());


        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

    }


}