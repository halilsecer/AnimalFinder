package com.example.homework1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Game extends AppCompatActivity implements TextToSpeech.OnInitListener {

      public static Button btn;
     public static ArrayList<String> list ;
    public static int level=1 ;
    public static int position=0;
    public static boolean flag=true;
    public static     int counter=0;
    public static int point =0;
    public static TextToSpeech textToSpeech ;
    public static MediaPlayer mp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);
            list = new ArrayList<>();
            list.add("horse");
            list.add("bear");
            list.add("rooster");
            list.add("lion");
            list.add("donkey");
            list.add("elephant");
            list.add("crow");
            list.add("cow");
            list.add("cat");
             list.add("dog");
            list.add("wolf");
            list.add("bird");
            list.add("monkey");
            list.add("sheep");
            list.add("pig");
            list.add("owl");
            list.add("grasshopper");
            list.add("chicken");
            list.add("bee");


        Collections.shuffle(list);
        textToSpeech =  new TextToSpeech(this,this);


        btn = findViewById(R.id.button_start);
      btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setVisibility(View.INVISIBLE);
                ShowFragmentOne();
            }
        });

    }
    @Override
    public void onInit(int status) {
            textToSpeech.setLanguage(Locale.ENGLISH);
            if(status == textToSpeech.SUCCESS){
                textToSpeech.speak("Welcome to game. Press Start  Button to Find Animals", TextToSpeech.QUEUE_ADD, null);
            }
    }
    public void ShowFragmentOne(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_tutucu,new fragment_1());
        ft.commit();
     }

}