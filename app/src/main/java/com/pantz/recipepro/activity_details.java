package com.pantz.recipepro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class activity_details extends AppCompatActivity {

    private String textTitle, textExec, cat, elements, exectime, calories, difrate;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        FourthFragment fg = new FourthFragment();
       /* String text = fg.getDisplayText();

        System.out.println("Text test "+text);*/

        textTitle=getIntent().getStringExtra("TEXT");
        textExec=getIntent().getStringExtra("EXEC");
        calories=getIntent().getStringExtra("CAL");
        difrate = getIntent().getStringExtra("DIFRATE");
        exectime = getIntent().getStringExtra("EXECTIME").toString();
        cat = getIntent().getStringExtra("CAT");
        elements = getIntent().getStringExtra("ELEMENTS");



        System.out.println("txtTITLE "+textTitle);

        TextView view  = findViewById(R.id.txtTitle);
        view.setText(textTitle);

        TextView exec = findViewById(R.id.textExec);
        exec.setText("Elements:\n"+ elements+ "\n\n" +"Execution:\n"+textExec );

        TextView cal = findViewById(R.id.textView5);
        cal.setText(calories);

        TextView dif = findViewById(R.id.textView3);
        dif.setText(difrate);

        TextView sExecTime = findViewById(R.id.textView6);
        sExecTime.setText(exectime);

        TextView catTxt = findViewById(R.id.textView10);
        catTxt.setText(cat);





        exec.setMovementMethod(new ScrollingMovementMethod());

        System.out.println("From details exec "+textExec);

    }

}