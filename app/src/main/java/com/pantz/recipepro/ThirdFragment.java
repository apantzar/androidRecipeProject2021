package com.pantz.recipepro;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {



    /**
     * @author Anastasios Pantzartzis
     * Fragment Creation
     * Each Fragment will have a role ;)
     * @param savedInstanceState
     */
    Button generalBtn, passwordBtn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_third, container, false);


        generalBtn = v.findViewById(R.id.button3);
        passwordBtn = v.findViewById(R.id.button5);


        /**
         * @authors Anna Tzanakopoulou, Anastasios Pantzartzis
         *
         * Opens general fragment if user press the general button
         * GeneralFragments contains : username , edit username
         *
         *
         */
        generalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GeneralFragment generalFragment =  new GeneralFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.thrirdFragment, generalFragment);
                transaction.commit();

            }
        });




        /**
         * @authors Anna Tzanakopoulou, Anastasios Pantzartzis
         *
         * Opens password fragment if user press the button
         * GeneralFragments contains : username , edit username
         *
         *
         */

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PasswordFragment passwordFragment =  new PasswordFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.thrirdFragment, passwordFragment);
                transaction.commit();

            }
        });





        /**
         * @authors Anna Tzanakopoulou, Anastasios Pantzartzis
         *
         * Opens LogIn activity
         *
         *User signed out
         */
        Button signOutBtn = v.findViewById(R.id.buttonSignOut);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdFragment.this.getActivity(), ActivityLogin.class);
                startActivity(intent);

            }
        });

        return v;
    }
}