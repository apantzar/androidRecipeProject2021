package com.pantz.recipepro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.sql.SQLException;

import jp.wasabeef.blurry.Blurry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {






    private  Database qdb;
    private String displayText;
    private String exec;
    private String cal;
    private String difRate;
    private String execTime;
    private String elements;
    private String cat;
    private String path;

    SwipeMenuListView favList;
    String favArray[];
    Database db;
    ImageView imageView;
    TextView fillMeUp;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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




        View view =inflater.inflate(R.layout.fragment_second, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView4);
        fillMeUp = (TextView) view.findViewById(R.id.textView2);
        favList = (SwipeMenuListView) view.findViewById(R.id.listViewFav) ;
        db = new Database(getContext());
        favArray = db.getData("SELECT * FROM favorite_list ", "fav_title");
        Boolean areYouFull = db.isFavFull();
        ArrayAdapter<String> favAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, favArray);
        favList.setAdapter(favAdapter);

        if(areYouFull){

            imageView.setVisibility(View.GONE);
            fillMeUp.setVisibility(View.GONE);
        }


        favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                displayText= (String)favList.getItemAtPosition(position);

                qdb = new Database(getContext());
                exec = qdb.getExec("SELECT exec FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec" );
                cal = qdb.getExec("SELECT calories FROM bizRecipe where recipe_title like '%"+displayText+"%'","calories" );
                difRate = qdb.getExec("SELECT dif_rate  FROM bizRecipe where recipe_title like '%"+displayText+"%'","dif_rate" ).toString();
                execTime = qdb.getExec("SELECT exec_time  FROM bizRecipe where recipe_title like '%"+displayText+"%'","exec_time" ).toString();
                elements = qdb.getExec("SELECT _elements FROM bizRecipe where recipe_title like '%"+displayText+"%'","_elements" );
                cat = qdb.getExec("SELECT recipe_category FROM bizRecipe where recipe_title like '%"+displayText+"%'","recipe_category" );
                path = qdb.getExec("SELECT path FROM bizRecipe where recipe_title like '%"+displayText+"%'","path" );






                Intent intent = new Intent(SecondFragment.this.getActivity(), activity_details.class);
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




        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        view.getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


        favList.setMenuCreator(creator);




       favList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete case

                        displayText= (String)favList.getItemAtPosition(position);
                        System.out.println("I am the display text "+displayText);
                        db.deleteFromFav(displayText);
                        favArray = db.getData("SELECT * FROM favorite_list ", "fav_title");
                        Boolean areYouFull = db.isFavFull();
                        ArrayAdapter<String> favAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, favArray);
                        favList.setAdapter(favAdapter);


                        if(!areYouFull){

                            imageView.setVisibility(View.VISIBLE);
                            fillMeUp.setVisibility(View.VISIBLE);
                        }





                       /* Object toRemove = favAdapter.getItem(position);
                        favAdapter.remove((String) toRemove);*/

                    /*    try {
                            Database db = new Database(getContext());
                            db.deleteFromFav(displayText);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }*/

                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });









        return view;
    }


    public String[] getFavArray() {
        return favArray;
    }

    public void setFavArray() {

        db = new Database(getContext());
        favArray = db.getData("SELECT * FROM favorite_list ", "fav_title");


        System.out.println("SETTERRRRRRRRRRRRRRRRRRRRRRRRRRR");
       for(int i=0; i <favArray.length;i++){

           System.out.println(favArray[i]);
       }


    }
}