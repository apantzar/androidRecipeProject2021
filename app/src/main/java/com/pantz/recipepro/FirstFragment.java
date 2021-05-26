package com.pantz.recipepro;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


        r1 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r2 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r3 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r4 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r5 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r6 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r7 = random.nextInt(highLimit - lowLimit)+lowLimit;
        r8 = random.nextInt(highLimit - lowLimit)+lowLimit;




        db = new Database(getContext());
        array = db.getData("SELECT * from bizRecipe where _id ="+ r1 +" OR  _id="+ r2 +" OR _id="+ r3+
                " OR  _id="+ r4 +" OR _id="+ r5
                +" OR  _id="+ r6 +" OR _id="+ r7+" OR  _id="+ r8 , "recipe_title");

        System.out.println("r1, r2, r3, r4, r5, r6, r7, r8 "+ r1+" "+r2+" "+r3+" "+ r4+" "+r5+" "+r6 +" "+r7+" "+r8);


        for(int i=0; i<array.length; i++){
            System.out.println("This is Array: "+ array[i]);
        }








    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment






        return inflater.inflate(R.layout.fragment_first, container, false);
    }


}