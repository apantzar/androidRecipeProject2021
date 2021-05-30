package com.pantz.recipepro;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralFragment extends Fragment {

    private static boolean is_edit_mode;

    View viewC;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  EditText username;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  String fromUserPasser;

    public GeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment generalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralFragment newInstance(String param1, String param2) {
        GeneralFragment fragment = new GeneralFragment();
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
        //fromUserPasser = getArguments().getString("pass");

        MainActivity mn = new MainActivity();
    //    fromUserPasser= mn.getUsernameToEdit();
        viewC = inflater.inflate(R.layout.fragment_general, container, false);
        return viewC;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        final SharedPreferences pref = requireActivity().getSharedPreferences("Settings", requireActivity().MODE_PRIVATE);
        final String old_username = pref.getString("username", "");
        username = view.findViewById(R.id.username);
        if(!username.equals("")){
            username.setText(fromUserPasser);
        }

        username.setText(old_username);

        Button editProfile = view.findViewById(R.id.editProfile);

        Button btnEdit = view.findViewById(R.id.editProfile);


        /**
         * Edit username TextView
         * User can change the username
         * Important!! The username must be unique
         */


        is_edit_mode = false;

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_edit_mode) {
                    Database helper = new Database(requireContext());
                    SQLiteDatabase db = helper.getReadableDatabase();

                    String new_username = username.getText().toString();

                    SharedPreferences.Editor edit = pref.edit();

                    if(!helper.uniqueUsername(new_username)){
                        edit.putString("username", new_username);
                        edit.apply();



                        ContentValues cv = new ContentValues();
                        cv.put(Database.REGISTER_COLUMN_USERNAME, new_username);
                        db.update(Database.REGISTER_TABLE_NAME, cv, "username = ? ", new String[]{old_username});

                        username.setEnabled(false);
                        editProfile.setText("Edit");
                    } else {
                        Toast.makeText(getActivity(), "This username already exists. Please try another one.", Toast.LENGTH_SHORT).show();  //if the username is already in database
                    }
                } else {
                    is_edit_mode = true;
                    username.setEnabled(true);
                    editProfile.setText("Save");
                }
            }
        });





    }
}