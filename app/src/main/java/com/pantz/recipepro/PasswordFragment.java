package com.pantz.recipepro;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment {

    private static boolean is_edit_mode;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordFragment newInstance(String param1, String param2) {
        PasswordFragment fragment = new PasswordFragment();
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
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final SharedPreferences pref = requireActivity().getSharedPreferences("Settings", requireActivity().MODE_PRIVATE);
        final String username = pref.getString("username", "");

        EditText txt_old_password = view.findViewById(R.id.old_password);
        txt_old_password.setText("");

        final EditText txt_new_password = view.findViewById(R.id.new_password);
        txt_new_password.setText("");

        Button editProfile = view.findViewById(R.id.editProfile);

        is_edit_mode = false;

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_edit_mode) {
                    HashMe passwordToHash = new HashMe("");

                    String oldpassword = txt_old_password.getText().toString();

                    try {
                        oldpassword = passwordToHash.theHasher(oldpassword);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    Database helper = new Database(requireContext());
                    SQLiteDatabase db = helper.getReadableDatabase();

                    Boolean isMatching = helper.usernamePasswordMatch(username, oldpassword);
                    if (!isMatching){
                        Toast.makeText(requireContext(), "Old password is invalid", Toast.LENGTH_SHORT).show();

                        return;
                    }


                    String newpassword = txt_new_password.getText().toString();
                    if (!User.ValidPassword(newpassword)) {
                        Toast.makeText(requireContext(), "New password is not valid.", Toast.LENGTH_SHORT).show();

                        return;
                    }

                    try {
                        newpassword = passwordToHash.theHasher(newpassword);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }


                    ContentValues cv = new ContentValues();
                    cv.put(Database.REGISTER_COLUMN_PASSWORD, newpassword);
                    db.update(Database.REGISTER_TABLE_NAME, cv, "username = ? ", new String[]{username});

                    txt_old_password.setEnabled(false);
                    txt_new_password.setEnabled(false);
                    editProfile.setText("Edit");
                } else {
                    is_edit_mode = true;

                    txt_old_password.setEnabled(true);
                    txt_new_password.setEnabled(true);
                    editProfile.setText("Save");
                }
            }
        });
    }
}