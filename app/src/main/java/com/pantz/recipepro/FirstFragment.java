package com.pantz.recipepro;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment{

    private int r1,r2,r3,r4,r5,r6,r7,r8;
    Database db;
    private String[] array;

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

        System.out.println("r1, r2, r3, r4, r5, r6, r7, r8 "+ r1+" "+r2+" "+r3+" "+ r4+" "+r5+" "+r6 +" "+r7+" "+r8);


        for (String s : array) {
            System.out.println("This is Array: " + s);
        }


      //  System.out.println(array[0]);




        //giveTitlesNow();





    }




    private void giveTitlesNow(){


       /* TextView view = requireView().findViewById(R.id.recipe1txt);
        view.setText(array[0]);

        TextView view1 = getView().findViewById(R.id.recipe2txt);
        view1.setText(array[1]);
*/
        /*TextView view2 = getView().findViewById(R.id.recipe3txt);
        view2.setText(array[2]);

        TextView view3 = getView().findViewById(R.id.recipe4txt);
        view3.setText(array[3]);

        TextView view4 = getView().findViewById(R.id.recipe5txt);
        view4.setText(array[4]);


        TextView view5 = getView().findViewById(R.id.recipe6txt);
        view5.setText(array[5]);


        TextView view6 = getView().findViewById(R.id.recipe7txt);
        view6.setText(array[6]);

        TextView view7 = getView().findViewById(R.id.recipe8txt);
        view7.setText(array[7]);

*/
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





        return view;
    }


}