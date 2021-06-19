package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultAct extends AppCompatActivity {
   /* String point = getIntent().getStringExtra("point");
    View str ;
*/    private TextView bestpoint;
    private TextView res;
    SharedPreferences preferences ;
    int yuksek;

    public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
       // Intent intent = getIntent();

        int point = getIntent().getIntExtra("point",0);
        res =findViewById(R.id.textView_result);

          preferences = getSharedPreferences("game", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        yuksek = preferences.getInt("game",0);

//            int strint = Integer.parseInt(Bestpoint);

            if(point>yuksek){
                 yuksek=point;
                res.setText("NEW HIGH SCORE  "+yuksek);
                editor.putInt( "game",yuksek);
                editor.commit();
            }

        res.setText("HİGH SCORE:   "+yuksek);
        bestpoint = findViewById(R.id.textView_score);
        bestpoint.setText("YOUR POINT  "+point);
/*
        if(Bestpoint==0){
            System.out.println("//////////////////////-");
            if(point > Bestpoint){
                preferences.edit()
                        .putInt("bestpoint", point)
                        .commit();
            }
        }
        else {            System.out.println("*************************//////////////////////////");

         /*   preferences.edit()
                    .putInt("bestpoint", point)
                    .commit();
        }

        System.out.println("*******************"+point);
        System.out.println("*******************"+Bestpoint);

        SharedPreferences prefs = getSharedPreferences("bestpoint", MODE_PRIVATE);
        String str = prefs.getString("bestpoint", "No name defined");//"No name defined" is the default value.
        SharedPreferences.Editor editor = getSharedPreferences("bestpoint", MODE_PRIVATE).edit();
         editor.apply();*/
    }
}