package com.example.homework1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class fragment4 extends Fragment {


    public fragment4() {
        // Required empty public constructor
    }

    private Button bt;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private CountDownTimer countDownTimer;


    private Random random = new Random();
    private TextView soru;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level4, container, false);

        image1 = rootView.findViewById(R.id.level4_4);
        image2 = rootView.findViewById(R.id.level4_3);
        image3 = rootView.findViewById(R.id.level4_2);
        image4 = rootView.findViewById(R.id.level4_1);
        image5 = rootView.findViewById(R.id.level4_5);
        image6 = rootView.findViewById(R.id.level4_6);
        soru = rootView.findViewById(R.id.textsoru44);

        String dogru = Game.list.get(Game.position);
        soru.setText(dogru);
        ArrayList<String> siklar = new ArrayList();
        siklar.add(dogru);
        countDownTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                soru.setText("TIME: "+String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {

                countDownTimer.cancel();


                startActivity(new Intent(getActivity(),ResultAct.class));
                getActivity().finish();
            }
        }.start();
        Game.textToSpeech.speak("find the"+dogru, TextToSpeech.QUEUE_FLUSH,null);
       // Game.mp = MediaPlayer.create(getActivity(),getResources().getIdentifier(dogru,"raw",getActivity().getPackageName()));
       // Game.mp.start();
        while(siklar.size()<6){
            int rnd = random.nextInt(15);
            while(!(Game.list.get(rnd)).equals(dogru) && !siklar.contains(Game.list.get(rnd))){
                siklar.add(Game.list.get(rnd));
                break;
            }

        }
        Collections.shuffle(siklar);
        int imageresource = getResources().getIdentifier(siklar.get(0), "drawable", getActivity().getPackageName());
        image1.setImageResource(imageresource);
        int imageresource2 = getResources().getIdentifier(siklar.get(1), "drawable", getActivity().getPackageName());
        image2.setImageResource(imageresource2);
        int imageresource3 = getResources().getIdentifier(siklar.get(2), "drawable", getActivity().getPackageName());
        image3.setImageResource(imageresource3);
        int imageresource4 = getResources().getIdentifier(siklar.get(3), "drawable", getActivity().getPackageName());
        image4.setImageResource(imageresource4);
        int imageresource5 = getResources().getIdentifier(siklar.get(4), "drawable", getActivity().getPackageName());
        image5.setImageResource(imageresource5);
        int imageresource6 = getResources().getIdentifier(siklar.get(5), "drawable", getActivity().getPackageName());
        image6.setImageResource(imageresource6);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                countDownTimer.cancel();
                check(siklar.get(0));
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(1));
                countDownTimer.cancel();

            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(2));
                countDownTimer.cancel();

            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(3));
                countDownTimer.cancel();

            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(4));
                countDownTimer.cancel();

            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(5));
                countDownTimer.cancel();

            }
        });
        return rootView;
    }

    public void check(String cevap){
        if(Game.list.get(Game.position).equals(cevap) ){
            Game.position++;
            Game.counter++;
            Game.point++;
          //  Game.mp.release();

            if (Game.counter==3){
                Game.level++;
                replace();
                Game.counter=0;
            }
            else {
                refresh();
            }
        }
        else {           // Game.mp.release();

            Intent intent = new Intent(getActivity(), ResultAct.class);
            intent.putExtra("point",Game.point);
            startActivity(intent);
//            startActivity(new Intent(getActivity(),ResultAct.class));
        }
    }

    public void refresh(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment4())
                .remove(fragment4.this)
                .commit();
    }

    public void replace(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment5())
                .remove(fragment4.this)
                .commit();
    }
}