package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;




public class ActivityHome extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button button = (Button)findViewById(R.id.button2);

        button.setOnClickListener(v -> {


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
            queue.add(stringRequest);


        });


    }


}
