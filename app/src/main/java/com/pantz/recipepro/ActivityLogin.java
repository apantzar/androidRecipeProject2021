package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    com.example.recipepro.DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new com.example.recipepro.DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(ActivityLogin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(ActivityLogin.this, "Sign in succesfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ActivityLogin.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}