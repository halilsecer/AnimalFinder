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


public class fragment5 extends Fragment {


    public fragment5() {
        // Required empty public constructor
    }


    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView image5;
    private ImageView image6;
    private ImageView image7;
    private ImageView image8;
    private CountDownTimer countDownTimer;

    private Random random = new Random();
    private TextView soru;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_level5, container, false);

        image1 = rootView.findViewById(R.id.level5_4);
        image2 = rootView.findViewById(R.id.level5_3);
        image3 = rootView.findViewById(R.id.level5_2);
        image4 = rootView.findViewById(R.id.level5_1);
        image5 = rootView.findViewById(R.id.level5_5);
        image6 = rootView.findViewById(R.id.level5_6);
        image7 = rootView.findViewById(R.id.level5_7);
        image8 = rootView.findViewById(R.id.level5_8);

        soru = rootView.findViewById(R.id.textViewsoru5);

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
        while(siklar.size()<8){
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
        int imageresource7 = getResources().getIdentifier(siklar.get(6), "drawable", getActivity().getPackageName());
        image7.setImageResource(imageresource7);
        int imageresource8 = getResources().getIdentifier(siklar.get(7), "drawable", getActivity().getPackageName());
        image8.setImageResource(imageresource8);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(0));
                countDownTimer.cancel();

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
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(6));
                countDownTimer.cancel();

            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//Game.mp.release();
                check(siklar.get(7));
                countDownTimer.cancel();

            }
        });
        return rootView;
    }

    public void check(String cevap){
        if(Game.list.get(Game.position).equals(cevap) ){
          //  Game.mp.release();
            Game.position++;
            Game.counter++;
            Game.point++;

            if (Game.counter==3){
                Game.level++;
                Game.counter=0;
                String str = String.valueOf(Game.point);
                Intent intent = new Intent(getActivity(), ResultAct.class);
                intent.putExtra("point", str);
                startActivity(intent);
            }
            else {
                refresh();
            }
        }
        else {
           // Game.mp.release();
            Intent intent = new Intent(getActivity(), ResultAct.class);
            intent.putExtra("point",Game.point);
            startActivity(intent);
        }
    }

    public void refresh(){
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_tutucu, new fragment5())
                .remove(fragment5.this)
                .commit();
    }

}