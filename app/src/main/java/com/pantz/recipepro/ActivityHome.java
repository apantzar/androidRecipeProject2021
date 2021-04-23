package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ActivityHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

     


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(ActivityHome.this);
                String url ="https://www.google.com"; //here the request

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Toast.makeText(ActivityHome.this, response , Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(ActivityHome.this, "Oh noooooo" , Toast.LENGTH_LONG).show();


                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);


            }
        });





    }


}
