package com.pantz.recipepro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FourthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourthFragment extends Fragment{


    private  String [] array;


    private String displayText;
    private String exec;
    private String cal;
    private String difRate;
    private String execTime;
    private String elements;
    private String cat;
    private String path;



    private  Database qdb;
    private  Database db;
    private Activity class2;
    public static ArrayList<String> list = new ArrayList<>();
    private ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

       class2 = new Activity();




/*
        SearchView view = (SearchView)getSomething();
        String givenString= view.getQuery().toString();


        System.out.println("The given "+givenString);*/





    }
    public AutoCompleteTextView getSomething(){
        return class2.findViewById(R.id.searchView);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
/*
        ActivityHome home = new ActivityHome();
        home.getIdOfBox();*/


      /*  db = new Database(getContext());
        array = db.getData("SELECT * FROM bizRecipe", "recipe_title" );*/
       /* System.out.println("From array: ");
         for(int i=0; i<array.length; i++){

             System.out.println(array[i]);
         }*/
        //Cursor cursor= db.readTheData("SELECT * FROM ");
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_fourth, container, false);


         listView = (ListView) view.findViewById(R.id.mainView);
/*
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                array


        );*/

        //listView.setAdapter(listViewAdapter);


        return view;



    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        SearchView viewid = getView().findViewById(R.id.searchView);
        ListView list = getView().findViewById(R.id.mainView);



        viewid.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                db = new Database(getContext());
                array = db.getData("SELECT * FROM bizRecipe where basic_element like '%"+newText+ "%' OR recipe_title like '%"+newText+"%'", "recipe_title" );
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,array);
                listView.setAdapter(adapter);

                adapter.getFilter().filter(newText);
                return false;
            }


        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                displayText= (String)listView.getItemAtPosition(position);

                System.out.println("DSPTEXT "+displayText );

               // startActivity(new Intent(FourthFragment.this.getActivity(), activity_details.class));

                qdb = new Database(getContext());
                exec = qdb.getExec("SELECT exec FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec" );
                cal = qdb.getExec("SELECT calories FROM bizRecipe where recipe_title like '%"+displayText+"%'","calories" );
                difRate = qdb.getExec("SELECT dif_rate  FROM bizRecipe where recipe_title like '%"+displayText+"%'","dif_rate" ).toString();
                execTime = qdb.getExec("SELECT exec_time  FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec_time" ).toString();
                elements = qdb.getExec("SELECT _elements FROM bizRecipe where recipe_title like '%"+displayText+"%'","_elements" );
                cat = qdb.getExec("SELECT recipe_category FROM bizRecipe where recipe_title like '%"+displayText+"%'","recipe_category" );
                path = qdb.getExec("SELECT path FROM bizRecipe where recipe_title like '%"+displayText+"%'","path" );






                Intent intent = new Intent(FourthFragment.this.getActivity(), activity_details.class);
                intent.putExtra("TEXT", displayText);
                intent.putExtra("EXEC", exec);
                intent.putExtra("CAL", cal);
                intent.putExtra("DIFRATE", difRate);
                intent.putExtra("EXECTIME",execTime);
                intent.putExtra("ELEMENTS",elements);
                intent.putExtra("CAT",cat);
                intent.putExtra("IMAGE", path);



                System.out.println(exec);
                System.out.println(cal);
                startActivity(intent);

            }
        });



    }

    public String getDisplayText() {

        return displayText;
    }


}