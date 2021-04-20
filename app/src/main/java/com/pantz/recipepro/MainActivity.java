package com.pantz.recipepro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;

    com.pantz.recipepro.DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SQL conn

        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("dbRecipe.bd", MODE_PRIVATE, null);


        //test
        /*-----------------------START OF LOG-IN----------------------------*/
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password =  findViewById(R.id.password);
        repassword =  findViewById(R.id.repassword);
        signup =  findViewById(R.id.btnsignup);
        signin =  findViewById(R.id.btnsignin);
      //  DB = new DBHelper(this);



        //----------------BUTTON LISTENERS-----------------------
        signup.setOnClickListener(v -> {

                    User user = new User(username.getText().toString(), password.getText().toString(), repassword.getText().toString());
                    if (user.getUsername().equals("") || user.getPassword().equals("") || user.getRepassword().equals("")) {
                        Toast.makeText(MainActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (user.getPassword().equals(user.getRepassword())) {
                            // Boolean checkusers = DB.checkusername(user);
                   /* if (checkusers == false) {
                        Boolean insert = DB.insertData(user, pass);
                        if (insert == true) {
                            Toast.makeText(MainActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }*/
                        }
                    }
                });


        signin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(intent);
        });
        /*-----------------------END OF LOG-IN---------------------------------*/
    }


}

