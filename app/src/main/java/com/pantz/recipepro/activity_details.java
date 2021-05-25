package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activity_details extends AppCompatActivity {

    private String textTitle, textExec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FourthFragment fg = new FourthFragment();
       /* String text = fg.getDisplayText();

        System.out.println("Text test "+text);*/

        textTitle=getIntent().getStringExtra("TEXT");
        textExec=getIntent().getStringExtra("EXEC");


        System.out.println("txtTITLE "+textTitle);

        TextView view  = findViewById(R.id.txtTitle);
        view.setText(textTitle);

        TextView exec = findViewById(R.id.textExec);
        exec.setText(textExec);

        System.out.println("From details exec "+textExec);

    }

}