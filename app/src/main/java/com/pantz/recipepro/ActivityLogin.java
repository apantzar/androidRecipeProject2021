package com.pantz.recipepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username =  findViewById(R.id.username1);
        password =  findViewById(R.id.password1);
        btnlogin =  findViewById(R.id.btnsignin1);




        // ######## try to login last user  ###########


        final SharedPreferences pref = getSharedPreferences("Settings", MODE_PRIVATE);
        User user = new User(pref.getString("username", ""), pref.getString("password", ""));
        Database Database = new Database(ActivityLogin.this);
        HashMe passwordToHash = new HashMe(user.getPassword());
        try {
            String hashedPassword = passwordToHash.theHasher(user.getPassword());
            user.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Boolean isMatching = Database.usernamePasswordMatch(user.getUsername(), user.getPassword());
        if(isMatching){
            Toast.makeText(ActivityLogin.this, "Successful sign in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
            startActivity(intent);
        }
        else{
            //remove saved login info
            SharedPreferences.Editor edit = pref.edit();

            edit.putString("username", "");
            edit.putString("password", "");
            edit.apply();
        }

        // ######## try to login last user  ###########





        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = new User(username.getText().toString(), password.getText().toString());
                if (user.getUsername().equals("") || user.getPassword().equals("")) {
                    Toast.makeText(ActivityLogin.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Database Database = new Database(ActivityLogin.this);
                    HashMe passwordToHash = new HashMe(user.getPassword());
                    try {
                        String hashedPassword = passwordToHash.theHasher(user.getPassword());
                        user.setPassword(hashedPassword);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    Boolean isMatching = Database.usernamePasswordMatch(user.getUsername(), user.getPassword());
                    if(isMatching){
                        //store saved login info
                        SharedPreferences.Editor edit = pref.edit();

                        edit.putString("username", user.getUsername());
                        edit.putString("password", password.getText().toString());
                        edit.apply();

                        Toast.makeText(ActivityLogin.this, "Successful sign in", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ActivityLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }



        });

    }





}