package com.pantz.recipepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQL conn

       SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("dbRecipe.bd", MODE_PRIVATE, null);

        
        /*-----------------------START OF LOG-IN----------------------------*/


        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password =  findViewById(R.id.password);
        repassword =  findViewById(R.id.repassword);
        signup =  findViewById(R.id.btnsignup);

        

        //----------------BUTTON LISTENERS-----------------------
        signup.setOnClickListener(v -> {

                    User user = new User(username.getText().toString(), password.getText().toString(), repassword.getText().toString());
                    if (user.getUsername().equals("") || user.getPassword().equals("") || user.getRepassword().equals("")) {
                        Toast.makeText(MainActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        Database Database = new Database(MainActivity.this);
                        Boolean isUniqueUser = Database.uniqueUsername(user.getUsername());
                        if(!isUniqueUser) {
                            if (user.ValidPassword(user.getPassword())) {
                                if (user.getPassword().equals(user.getRepassword())) {
                                    HashMe passwordToHash = new HashMe(user.getPassword());
                                    try {
                                        String hashedPassword = passwordToHash.theHasher(user.getPassword());
                                        user.setPassword(hashedPassword);
                                    } catch (NoSuchAlgorithmException e) {
                                        e.printStackTrace();
                                    }
                                    boolean successRegister = Database.addElement(user);
                                    if (successRegister) {
                                        final SharedPreferences pref = getSharedPreferences("Settings", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = pref.edit();

                                        edit.putString("username", user.getUsername());
                                        edit.putString("password", password.getText().toString());
                                        edit.apply();

                                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Not matching passwords.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Not valid password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "This username already exists. Please try another one.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        /*
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
                finish();
            }


        });*/
        /*-----------------------END OF LOG-IN---------------------------------*/



        TextView btn = (TextView)findViewById(R.id.textView8);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivityLogin.class));
            }
        });


    }


}

