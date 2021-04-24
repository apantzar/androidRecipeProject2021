package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class ActivityHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(v -> {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);






            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://tasty.p.rapidapi.com/tags/list")
                    .get()
                    .addHeader("x-rapidapi-key", "26a22ee7a7msh03d13c0b22a034cp1317e8jsn479f9b5ec601")
                    .addHeader("x-rapidapi-host", "tasty.p.rapidapi.com")
                    .build();

            try {
                Response response = client.newCall(request).execute();

                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Oh noooo");
            }


            /*
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(ActivityHome.this);
            String url ="http://api.yummly.com/v1/api/recipe/recipe-id?_app_id=default-application_5197045&_app_key=26a22ee7a7msh03d13c0b22a034cp1317e8jsn479f9b5ec601";
            //here the request

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
            queue.add(stringRequest);*/


        });




    }


}
